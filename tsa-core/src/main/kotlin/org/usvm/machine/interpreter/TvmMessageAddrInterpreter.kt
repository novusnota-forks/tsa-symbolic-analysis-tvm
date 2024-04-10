package org.usvm.machine.interpreter

import io.ksmt.utils.asExpr
import org.ton.bytecode.TvmAppAddrInst
import org.ton.bytecode.TvmAppAddrLdmsgaddrInst
import org.ton.bytecode.TvmAppAddrRewritestdaddrInst
import org.ton.bytecode.TvmArtificialLoadAddrExternInst
import org.ton.bytecode.TvmArtificialLoadAddrInst
import org.ton.bytecode.TvmArtificialLoadAddrNoneInst
import org.ton.bytecode.TvmArtificialLoadAddrStdInst
import org.ton.bytecode.TvmArtificialLoadAddrVarInst
import org.ton.bytecode.TvmCellType
import org.ton.bytecode.TvmIntegerType
import org.ton.bytecode.TvmSliceType
import org.usvm.UExpr
import org.usvm.UHeapRef
import org.usvm.api.readField
import org.usvm.api.writeField
import org.usvm.logger
import org.usvm.machine.TvmContext
import org.usvm.machine.TvmSizeSort
import org.usvm.machine.state.allocSliceFromCell
import org.usvm.machine.state.builderCopy
import org.usvm.machine.state.calcOnStateCtx
import org.usvm.machine.state.consumeDefaultGas
import org.usvm.machine.state.doWithStateCtx
import org.usvm.machine.state.newStmt
import org.usvm.machine.state.nextStmt
import org.usvm.machine.state.sliceCopy
import org.usvm.machine.state.sliceMoveDataPtr
import org.usvm.machine.state.slicePreloadDataBits
import org.usvm.machine.state.takeLastSlice
import org.usvm.mkSizeAddExpr
import org.usvm.mkSizeExpr
import org.usvm.sizeSort

class TvmMessageAddrInterpreter(private val ctx: TvmContext) {
    fun visitAddrInst(scope: TvmStepScope, stmt: TvmAppAddrInst) {
        scope.consumeDefaultGas(stmt)

        when (stmt) {
            is TvmAppAddrLdmsgaddrInst -> visitLoadMessageAddrInst(scope, stmt)
            is TvmAppAddrRewritestdaddrInst -> visitParseStdAddr(scope, stmt)
            is TvmArtificialLoadAddrInst -> visitArtificialLoadAddrInst(scope, stmt)
            else -> TODO("$stmt")
        }
    }

    private fun visitLoadMessageAddrInst(scope: TvmStepScope, inst: TvmAppAddrLdmsgaddrInst) {
        scope.doWithStateCtx {
            val slice = stack.takeLastSlice()
            val updatedSlice = memory.allocConcrete(TvmSliceType).also { sliceCopy(slice, it) }

            stack.add(slice, TvmSliceType)
            stack.add(updatedSlice, TvmSliceType)

            scope.loadMessageAddr(updatedSlice, inst)
        }
    }

    private fun visitArtificialLoadAddrInst(scope: TvmStepScope, inst: TvmArtificialLoadAddrInst) {
        when (inst) {
            is TvmArtificialLoadAddrNoneInst -> visitLoadAddrNone(scope, inst)
            is TvmArtificialLoadAddrExternInst -> visitLoadAddrExtern(scope, inst)
            is TvmArtificialLoadAddrStdInst -> visitLoadAddrStd(scope, inst)
            is TvmArtificialLoadAddrVarInst -> visitLoadAddrVar(scope, inst)
        }
    }

    private fun visitLoadAddrNone(scope: TvmStepScope, inst: TvmArtificialLoadAddrNoneInst) {
        scope.doWithStateCtx {
            val updatedSlice = stack.takeLastSlice()
            val originalSlice = stack.takeLastSlice()

            val prefixSlice = scope.readMessageTail(
                originalSlice = originalSlice,
                updatedSlice = updatedSlice,
                addrLen = mkSizeExpr(0),
                addrLengthBits = 0,
                containsAnyCast = false
            ) ?: return@doWithStateCtx

            stack.add(prefixSlice, TvmSliceType)
            stack.add(updatedSlice, TvmSliceType)

            newStmt(inst.nextStmt())
        }
    }

    private fun visitLoadAddrExtern(scope: TvmStepScope, inst: TvmArtificialLoadAddrExternInst) {
        scope.doWithStateCtx {
            val updatedSlice = stack.takeLastSlice()
            val originalSlice = stack.takeLastSlice()

            val addrLen = scope.loadAddrLen(updatedSlice)
                ?: return@doWithStateCtx

            val prefixSlice = scope.readMessageTail(originalSlice, updatedSlice, addrLen, addrLengthBits = 9, containsAnyCast = false)
                ?: return@doWithStateCtx

            stack.add(prefixSlice, TvmSliceType)
            stack.add(updatedSlice, TvmSliceType)

            newStmt(inst.nextStmt())
        }
    }

    private fun visitLoadAddrStd(scope: TvmStepScope, inst: TvmArtificialLoadAddrStdInst) {
        scope.doWithStateCtx {
            val updatedSlice = stack.takeLastSlice()
            val originalSlice = stack.takeLastSlice()

            scope.readMaybeAnycast(updatedSlice)

            val addrLen = mkSizeExpr(8 + 256)
            val prefixSlice = scope.readMessageTail(originalSlice, updatedSlice, addrLen, addrLengthBits = 0, containsAnyCast = true)
                ?: return@doWithStateCtx

            stack.add(prefixSlice, TvmSliceType)
            stack.add(updatedSlice, TvmSliceType)

            newStmt(inst.nextStmt())
        }
    }

    private fun visitLoadAddrVar(scope: TvmStepScope, inst: TvmArtificialLoadAddrVarInst) {
        scope.doWithStateCtx {
            val updatedSlice = stack.takeLastSlice()
            val originalSlice = stack.takeLastSlice()

            scope.readMaybeAnycast(updatedSlice)

            val unbiasedAddrLen = scope.loadAddrLen(updatedSlice)
                ?: return@doWithStateCtx
            val addrLen = mkSizeAddExpr(unbiasedAddrLen, mkSizeExpr(32))

            val prefixSlice = scope.readMessageTail(originalSlice, updatedSlice, addrLen, addrLengthBits = 9, containsAnyCast = true)
                ?: return@doWithStateCtx

            stack.add(prefixSlice, TvmSliceType)
            stack.add(updatedSlice, TvmSliceType)

            newStmt(inst.nextStmt())
        }
    }

    private fun visitParseStdAddr(scope: TvmStepScope, inst: TvmAppAddrRewritestdaddrInst) {
        scope.doWithStateCtx {
            val slice = stack.takeLastSlice()
            val copySlice = memory.allocConcrete(TvmSliceType).also { sliceCopy(slice, it) }
            val addrConstructor = scope.slicePreloadDataBits(copySlice, bits = 2)
                ?: TODO("Deal with incorrect address")

            scope.assert(addrConstructor eq mkBv(value = 2, sizeBits = 2u))
                ?: run {
                    // TODO Deal with non addr_std
                    logger.debug { "Non-std addr found, dropping the state" }
                    return@doWithStateCtx
                }

            sliceMoveDataPtr(copySlice, bits = 2)

            val workchain = scope.slicePreloadDataBits(copySlice, bits = 8)
                ?: TODO("Deal with incorrect address")
            sliceMoveDataPtr(copySlice, bits = 8)

            val address = scope.slicePreloadDataBits(copySlice, bits = 256)
                ?: TODO("Deal with incorrect address")

            stack.add(workchain.signedExtendToInteger(), TvmIntegerType)
            stack.add(address.unsignedExtendToInteger(), TvmIntegerType)

            newStmt(inst.nextStmt())
        }
    }

    private fun TvmStepScope.readMaybeAnycast(slice: UHeapRef) {
        doWithStateCtx {
            val maybeAnycast = slicePreloadDataBits(slice, bits = 1)
                ?: return@doWithStateCtx
            assert(maybeAnycast eq mkBv(value = 0, sizeBits = 1u))
                ?: TODO("Support presented anycast")
            sliceMoveDataPtr(slice, bits = 1)
        }
    }

    private fun TvmStepScope.readMessageTail(
        originalSlice: UHeapRef,
        updatedSlice: UHeapRef,
        addrLen: UExpr<TvmSizeSort>,
        addrLengthBits: Int,
        containsAnyCast: Boolean
    ): UHeapRef? = calcOnStateCtx {
        val anycastShift = if (containsAnyCast) 1 else 0

        if (addrLengthBits > 0) {
            sliceMoveDataPtr(updatedSlice, addrLengthBits)
        }

        val bitsToReadLength = mkSizeAddExpr(mkSizeExpr(2 + anycastShift + addrLengthBits), addrLen)
        val msgAddr = slicePreloadDataBits(originalSlice, bitsToReadLength)
            ?: return@calcOnStateCtx null

        sliceMoveDataPtr(updatedSlice, addrLen)

        val cell = memory.readField(originalSlice, TvmContext.sliceCellField, addressSort)
        val updatedCell = memory.allocConcrete(TvmCellType).also { builderCopy(cell, it) }

        memory.writeField(updatedCell, TvmContext.cellDataField, cellDataSort, msgAddr, guard = trueExpr)
        memory.writeField(updatedCell, TvmContext.cellDataLengthField, sizeSort, bitsToReadLength, guard = trueExpr)

        val prefixSlice = allocSliceFromCell(updatedCell)

        prefixSlice
    }

    private fun TvmStepScope.loadAddrLen(slice: UHeapRef): UExpr<TvmSizeSort>? {
        val addrLen = slicePreloadDataBits(slice, bits = 9)
            ?: return null

        return ctx.mkBvZeroExtensionExpr((ctx.sizeSort.sizeBits - addrLen.sort.sizeBits).toInt(), addrLen).asExpr(ctx.sizeSort)
    }

    private fun TvmStepScope.loadMessageAddr(updatedSlice: UHeapRef, inst: TvmAppAddrLdmsgaddrInst) = calcOnStateCtx {
        val prefix = slicePreloadDataBits(updatedSlice, bits = 2)
            ?: return@calcOnStateCtx

        val addrNone = mkBv(value = 0, sizeBits = 2u)
        val addrExtern = mkBv(value = 1, sizeBits = 2u)
        val addrStd = mkBv(value = 2, sizeBits = 2u)
        val addrVar = mkBv(value = 3, sizeBits = 2u)

        sliceMoveDataPtr(updatedSlice, bits = 2)

        forkMulti(
            listOf(
                (prefix eq addrNone) to {
                    newStmt(TvmArtificialLoadAddrNoneInst(inst))
                },
                (prefix eq addrExtern) to {
                    newStmt(TvmArtificialLoadAddrExternInst(inst))
                },
                (prefix eq addrStd) to {
                    newStmt(TvmArtificialLoadAddrStdInst(inst))
                },
                (prefix eq addrVar) to {
                    newStmt(TvmArtificialLoadAddrVarInst(inst))
                },
            )
        )
    }
}