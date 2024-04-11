package org.usvm.machine.interpreter

import org.ton.bytecode.TvmArithmDivAdddivmodInst
import org.ton.bytecode.TvmArithmDivAdddivmodcInst
import org.ton.bytecode.TvmArithmDivAdddivmodrInst
import org.ton.bytecode.TvmArithmDivAddrshiftcmodInst
import org.ton.bytecode.TvmArithmDivAddrshiftmodInst
import org.ton.bytecode.TvmArithmDivAddrshiftmodVarInst
import org.ton.bytecode.TvmArithmDivAddrshiftmodcInst
import org.ton.bytecode.TvmArithmDivAddrshiftmodrInst
import org.ton.bytecode.TvmArithmDivAddrshiftrmodInst
import org.ton.bytecode.TvmArithmDivDivInst
import org.ton.bytecode.TvmArithmDivDivcInst
import org.ton.bytecode.TvmArithmDivDivmodInst
import org.ton.bytecode.TvmArithmDivDivmodcInst
import org.ton.bytecode.TvmArithmDivDivmodrInst
import org.ton.bytecode.TvmArithmDivDivrInst
import org.ton.bytecode.TvmArithmDivInst
import org.ton.bytecode.TvmArithmDivLshiftadddivmodInst
import org.ton.bytecode.TvmArithmDivLshiftadddivmodVarInst
import org.ton.bytecode.TvmArithmDivLshiftadddivmodcInst
import org.ton.bytecode.TvmArithmDivLshiftadddivmodcVarInst
import org.ton.bytecode.TvmArithmDivLshiftadddivmodrInst
import org.ton.bytecode.TvmArithmDivLshiftadddivmodrVarInst
import org.ton.bytecode.TvmArithmDivLshiftdivInst
import org.ton.bytecode.TvmArithmDivLshiftdivVarInst
import org.ton.bytecode.TvmArithmDivLshiftdivcInst
import org.ton.bytecode.TvmArithmDivLshiftdivcVarInst
import org.ton.bytecode.TvmArithmDivLshiftdivmodInst
import org.ton.bytecode.TvmArithmDivLshiftdivmodVarInst
import org.ton.bytecode.TvmArithmDivLshiftdivmodcInst
import org.ton.bytecode.TvmArithmDivLshiftdivmodcVarInst
import org.ton.bytecode.TvmArithmDivLshiftdivmodrInst
import org.ton.bytecode.TvmArithmDivLshiftdivmodrVarInst
import org.ton.bytecode.TvmArithmDivLshiftdivrInst
import org.ton.bytecode.TvmArithmDivLshiftdivrVarInst
import org.ton.bytecode.TvmArithmDivLshiftmodInst
import org.ton.bytecode.TvmArithmDivLshiftmodVarInst
import org.ton.bytecode.TvmArithmDivLshiftmodcInst
import org.ton.bytecode.TvmArithmDivLshiftmodcVarInst
import org.ton.bytecode.TvmArithmDivLshiftmodrInst
import org.ton.bytecode.TvmArithmDivLshiftmodrVarInst
import org.ton.bytecode.TvmArithmDivModInst
import org.ton.bytecode.TvmArithmDivModcInst
import org.ton.bytecode.TvmArithmDivModpow2Inst
import org.ton.bytecode.TvmArithmDivModpow2VarInst
import org.ton.bytecode.TvmArithmDivModpow2cInst
import org.ton.bytecode.TvmArithmDivModpow2cVarInst
import org.ton.bytecode.TvmArithmDivModpow2rInst
import org.ton.bytecode.TvmArithmDivModpow2rVarInst
import org.ton.bytecode.TvmArithmDivModrInst
import org.ton.bytecode.TvmArithmDivMuladddivmodInst
import org.ton.bytecode.TvmArithmDivMuladddivmodcInst
import org.ton.bytecode.TvmArithmDivMuladddivmodrInst
import org.ton.bytecode.TvmArithmDivMuladdrshiftcmodInst
import org.ton.bytecode.TvmArithmDivMuladdrshiftmodInst
import org.ton.bytecode.TvmArithmDivMuladdrshiftrmodInst
import org.ton.bytecode.TvmArithmDivMuldivInst
import org.ton.bytecode.TvmArithmDivMuldivcInst
import org.ton.bytecode.TvmArithmDivMuldivmodInst
import org.ton.bytecode.TvmArithmDivMuldivmodcInst
import org.ton.bytecode.TvmArithmDivMuldivmodrInst
import org.ton.bytecode.TvmArithmDivMuldivrInst
import org.ton.bytecode.TvmArithmDivMulmodInst
import org.ton.bytecode.TvmArithmDivMulmodcInst
import org.ton.bytecode.TvmArithmDivMulmodpow2Inst
import org.ton.bytecode.TvmArithmDivMulmodpow2VarInst
import org.ton.bytecode.TvmArithmDivMulmodpow2cInst
import org.ton.bytecode.TvmArithmDivMulmodpow2cVarInst
import org.ton.bytecode.TvmArithmDivMulmodpow2rInst
import org.ton.bytecode.TvmArithmDivMulmodpow2rVarInst
import org.ton.bytecode.TvmArithmDivMulmodrInst
import org.ton.bytecode.TvmArithmDivMulrshiftInst
import org.ton.bytecode.TvmArithmDivMulrshiftVarInst
import org.ton.bytecode.TvmArithmDivMulrshiftcInst
import org.ton.bytecode.TvmArithmDivMulrshiftcVarInst
import org.ton.bytecode.TvmArithmDivMulrshiftcmodInst
import org.ton.bytecode.TvmArithmDivMulrshiftcmodVarInst
import org.ton.bytecode.TvmArithmDivMulrshiftmodInst
import org.ton.bytecode.TvmArithmDivMulrshiftmodVarInst
import org.ton.bytecode.TvmArithmDivMulrshiftrInst
import org.ton.bytecode.TvmArithmDivMulrshiftrVarInst
import org.ton.bytecode.TvmArithmDivMulrshiftrmodInst
import org.ton.bytecode.TvmArithmDivMulrshiftrmodVarInst
import org.ton.bytecode.TvmArithmDivRshiftcInst
import org.ton.bytecode.TvmArithmDivRshiftcVarInst
import org.ton.bytecode.TvmArithmDivRshiftcmodInst
import org.ton.bytecode.TvmArithmDivRshiftmodInst
import org.ton.bytecode.TvmArithmDivRshiftmodVarInst
import org.ton.bytecode.TvmArithmDivRshiftmodcVarInst
import org.ton.bytecode.TvmArithmDivRshiftmodrVarInst
import org.ton.bytecode.TvmArithmDivRshiftrInst
import org.ton.bytecode.TvmArithmDivRshiftrVarInst
import org.ton.bytecode.TvmArithmDivRshiftrmodInst
import org.ton.bytecode.TvmIntegerType
import org.usvm.UBoolExpr
import org.usvm.UBvSort
import org.usvm.UExpr
import org.usvm.machine.TvmContext
import org.usvm.machine.TvmContext.TvmInt257Ext1Sort
import org.usvm.machine.TvmContext.TvmInt257Ext256Sort
import org.usvm.machine.TvmContext.TvmInt257Sort
import org.usvm.machine.state.TvmIntegerOutOfRange
import org.usvm.machine.state.TvmIntegerOverflow
import org.usvm.machine.state.bvMaxValueSignedExtended
import org.usvm.machine.state.bvMinValueSignedExtended
import org.usvm.machine.state.consumeDefaultGas
import org.usvm.machine.state.newStmt
import org.usvm.machine.state.nextStmt
import org.usvm.machine.state.setFailure
import org.usvm.machine.state.takeLastInt

class TvmArithDivInterpreter(private val ctx: TvmContext) {

    fun visitArithmeticDivInst(scope: TvmStepScope, stmt: TvmArithmDivInst) {
        scope.consumeDefaultGas(stmt)

        with(ctx) {
            when (stmt) {
                is TvmArithmDivDivInst -> {
                    val (x, y) = takeOperandsAndCheckForZero(scope) ?: return
                    val (div, noOverflow) = makeDiv(x, y)
                    checkOverflow(noOverflow, scope) ?: return
                    scope.calcOnState {
                        stack.add(div, TvmIntegerType)
                    }
                }
                is TvmArithmDivModInst -> {
                    val (x, y) = takeOperandsAndCheckForZero(scope) ?: return
                    scope.calcOnState {
                        stack.add(makeMod(x, y), TvmIntegerType)
                    }
                }
                is TvmArithmDivDivcInst -> {
                    val (x, y) = takeOperandsAndCheckForZero(scope) ?: return
                    val (divc, noOverflow) = makeDivc(x, y)
                    checkOverflow(noOverflow, scope) ?: return
                    scope.calcOnState {
                        stack.add(divc, TvmIntegerType)
                    }
                }
                is TvmArithmDivDivrInst -> {
                    val (x, y) = takeOperandsAndCheckForZero(scope) ?: return
                    val (divr, noOverflow) = makeDivr(x, y)
                    checkOverflow(noOverflow, scope) ?: return
                    scope.calcOnState {
                        stack.add(divr, TvmIntegerType)
                    }
                }
                is TvmArithmDivDivmodInst -> {
                    val (x, y) = takeOperandsAndCheckForZero(scope) ?: return
                    val (div, mod) = makeDivMod(x, y)
                    checkOverflow(div.noOverflow, scope) ?: return
                    scope.calcOnState {
                        stack.add(div.value, TvmIntegerType)
                        stack.add(mod, TvmIntegerType)
                    }
                }
                is TvmArithmDivDivmodcInst -> {
                    val (x, y) = takeOperandsAndCheckForZero(scope) ?: return
                    val (divc, modc) = makeDivModc(x, y)
                    checkOverflow(divc.noOverflow, scope) ?: return
                    scope.calcOnState {
                        stack.add(divc.value, TvmIntegerType)
                        stack.add(modc, TvmIntegerType)
                    }
                }
                is TvmArithmDivDivmodrInst -> {
                    val (x, y) = takeOperandsAndCheckForZero(scope) ?: return
                    val (divr, modr) = makeDivModr(x, y)
                    checkOverflow(divr.noOverflow, scope) ?: return
                    scope.calcOnState {
                        stack.add(divr.value, TvmIntegerType)
                        stack.add(modr, TvmIntegerType)
                    }
                }
                is TvmArithmDivModcInst -> {
                    val (x, y) = takeOperandsAndCheckForZero(scope) ?: return
                    scope.calcOnState {
                        stack.add(makeModc(x, y), TvmIntegerType)
                    }
                }
                is TvmArithmDivModrInst -> {
                    val (x, y) = takeOperandsAndCheckForZero(scope) ?: return
                    scope.calcOnState {
                        stack.add(makeModr(x, y), TvmIntegerType)
                    }
                }
                is TvmArithmDivAdddivmodInst -> {
                    doAdddivmodX(scope) { x, y -> makeDivMod(x, y) } ?: return
                }
                is TvmArithmDivAdddivmodcInst -> {
                    doAdddivmodX(scope) { x, y -> makeDivModc(x, y) } ?: return
                }
                is TvmArithmDivAdddivmodrInst -> {
                    doAdddivmodX(scope) { x, y -> makeDivModr(x, y) } ?: return
                }
                is TvmArithmDivModpow2Inst -> scope.doWithState {
                    doModpow2XNoVar(scope, stmt.t) { x, y -> makeMod(x, y) }
                }
                is TvmArithmDivModpow2cInst -> scope.doWithState {
                    doModpow2XNoVar(scope, stmt.t) { x, y -> makeModc(x, y) }
                }
                is TvmArithmDivModpow2rInst -> scope.doWithState {
                    doModpow2XNoVar(scope, stmt.t) { x, y -> makeModr(x, y) }
                }
                is TvmArithmDivModpow2VarInst -> {
                    doModpow2XVar(scope) { x, y -> makeMod(x, y) } ?: return
                }
                is TvmArithmDivModpow2cVarInst -> {
                    doModpow2XVar(scope) { x, y -> makeModc(x, y) } ?: return
                }
                is TvmArithmDivModpow2rVarInst -> {
                    doModpow2XVar(scope) { x, y -> makeModr(x, y) } ?: return
                }
                is TvmArithmDivRshiftcInst -> {
                    doRshiftXNoVar(stmt.t, scope) { x, y -> listOf(makeDivc(x, y).value) }
                }
                is TvmArithmDivRshiftrInst -> {
                    doRshiftXNoVar(stmt.t, scope) { x, y -> listOf(makeDivr(x, y).value) }
                }
                is TvmArithmDivRshiftcVarInst -> {
                    doRshiftXVar(scope) { x, y -> listOf(makeDivc(x, y).value) } ?: return
                }
                is TvmArithmDivRshiftrVarInst -> {
                    doRshiftXVar(scope) { x, y -> listOf(makeDivr(x, y).value) } ?: return
                }
                is TvmArithmDivRshiftmodInst -> {
                    doRshiftXNoVar(stmt.t, scope) { x, y ->
                        val (div, mod) = makeDivMod(x, y)
                        listOf(div.value, mod)
                    }
                }
                is TvmArithmDivRshiftcmodInst -> {
                    doRshiftXNoVar(stmt.t, scope) { x, y ->
                        val (div, mod) = makeDivModc(x, y)
                        listOf(div.value, mod)
                    }
                }
                is TvmArithmDivRshiftrmodInst -> {
                    doRshiftXNoVar(stmt.t, scope) { x, y ->
                        val (div, mod) = makeDivModr(x, y)
                        listOf(div.value, mod)
                    }
                }
                is TvmArithmDivRshiftmodVarInst -> {
                    doRshiftXVar(scope) { x, y ->
                        val (div, mod) = makeDivMod(x, y)
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivRshiftmodcVarInst -> {
                    doRshiftXVar(scope) { x, y ->
                        val (div, mod) = makeDivModc(x, y)
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivRshiftmodrVarInst -> {
                    doRshiftXVar(scope) { x, y ->
                        val (div, mod) = makeDivModr(x, y)
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivLshiftdivInst -> {
                    doLShiftXNoVar(scope, stmt.t) { x, y ->
                        val div = makeDiv(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXNoVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXNoVar null
                        listOf(div.value)
                    } ?: return
                }
                is TvmArithmDivLshiftdivcInst -> {
                    doLShiftXNoVar(scope, stmt.t) { x, y ->
                        val div = makeDivc(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXNoVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXNoVar null
                        listOf(div.value)
                    } ?: return
                }
                is TvmArithmDivLshiftdivrInst -> {
                    doLShiftXNoVar(scope, stmt.t) { x, y ->
                        val div = makeDivr(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXNoVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXNoVar null
                        listOf(div.value)
                    } ?: return
                }
                is TvmArithmDivLshiftdivVarInst -> {
                    doLShiftXVar(scope) { x, y ->
                        val div = makeDiv(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXVar null
                        listOf(div.value)
                    } ?: return
                }
                is TvmArithmDivLshiftdivcVarInst -> {
                    doLShiftXVar(scope) { x, y ->
                        val div = makeDivc(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXVar null
                        listOf(div.value)
                    } ?: return
                }
                is TvmArithmDivLshiftdivrVarInst -> {
                    doLShiftXVar(scope) { x, y ->
                        val div = makeDivr(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXVar null
                        listOf(div.value)
                    } ?: return
                }
                is TvmArithmDivLshiftdivmodInst -> {
                    doLShiftXNoVar(scope, stmt.t) { x, y ->
                        val (div, mod) = makeDivMod(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXNoVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXNoVar null
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivLshiftdivmodcInst -> {
                    doLShiftXNoVar(scope, stmt.t) { x, y ->
                        val (div, mod) = makeDivModc(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXNoVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXNoVar null
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivLshiftdivmodrInst -> {
                    doLShiftXNoVar(scope, stmt.t) { x, y ->
                        val (div, mod) = makeDivModr(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXNoVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXNoVar null
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivLshiftdivmodVarInst -> {
                    doLShiftXVar(scope) { x, y ->
                        val (div, mod) = makeDivMod(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXVar null
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivLshiftdivmodcVarInst -> {
                    doLShiftXVar(scope) { x, y ->
                        val (div, mod) = makeDivModc(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXVar null
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivLshiftdivmodrVarInst -> {
                    doLShiftXVar(scope) { x, y ->
                        val (div, mod) = makeDivModr(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doLShiftXVar null
                        checkInBounds(div.value, scope)
                            ?: return@doLShiftXVar null
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivLshiftmodInst -> {
                    doLShiftXNoVar(scope, stmt.t) { x, y ->
                        val mod = makeMod(x, y)
                        // no need for checkOverflow or checkInBounds
                        listOf(mod)
                    } ?: return
                }
                is TvmArithmDivLshiftmodcInst -> {
                    doLShiftXNoVar(scope, stmt.t) { x, y ->
                        val mod = makeModc(x, y)
                        // no need for checkOverflow or checkInBounds
                        listOf(mod)
                    } ?: return
                }
                is TvmArithmDivLshiftmodrInst -> {
                    doLShiftXNoVar(scope, stmt.t) { x, y ->
                        val mod = makeModr(x, y)
                        // no need for checkOverflow or checkInBounds
                        listOf(mod)
                    } ?: return
                }
                is TvmArithmDivLshiftmodVarInst -> {
                    doLShiftXVar(scope) { x, y ->
                        val mod = makeMod(x, y)
                        // no need for checkOverflow or checkInBounds
                        listOf(mod)
                    } ?: return
                }
                is TvmArithmDivLshiftmodcVarInst -> {
                    doLShiftXVar(scope) { x, y ->
                        val mod = makeModc(x, y)
                        // no need for checkOverflow or checkInBounds
                        listOf(mod)
                    } ?: return
                }
                is TvmArithmDivLshiftmodrVarInst -> {
                    doLShiftXVar(scope) { x, y ->
                        val mod = makeModr(x, y)
                        // no need for checkOverflow or checkInBounds
                        listOf(mod)
                    } ?: return
                }
                is TvmArithmDivAddrshiftmodInst -> {
                    doAddrshiftmodXNoVar(scope, stmt.t) { x, y ->
                        makeDivMod(x, y)
                    } ?: return
                }
                is TvmArithmDivAddrshiftcmodInst -> {
                    doAddrshiftmodXNoVar(scope, stmt.t) { x, y ->
                        makeDivModc(x, y)
                    } ?: return
                }
                is TvmArithmDivAddrshiftrmodInst -> {
                    doAddrshiftmodXNoVar(scope, stmt.t) { x, y ->
                        makeDivModr(x, y)
                    } ?: return
                }
                is TvmArithmDivAddrshiftmodVarInst -> {
                    doAddrshiftmodXVar(scope) { x, y ->
                        makeDivMod(x, y)
                    } ?: return
                }
                is TvmArithmDivAddrshiftmodcInst -> {
                    doAddrshiftmodXVar(scope) { x, y ->
                        makeDivModc(x, y)
                    } ?: return
                }
                is TvmArithmDivAddrshiftmodrInst -> {
                    doAddrshiftmodXVar(scope) { x, y ->
                        makeDivModr(x, y)
                    } ?: return
                }
                is TvmArithmDivLshiftadddivmodInst -> {
                    doLshiftadddivmodNoVar(scope, stmt.t) { x, y ->
                        makeDivMod(x, y)
                    } ?: return
                }
                is TvmArithmDivLshiftadddivmodcInst -> {
                    doLshiftadddivmodNoVar(scope, stmt.t) { x, y ->
                        makeDivModc(x, y)
                    } ?: return
                }
                is TvmArithmDivLshiftadddivmodrInst -> {
                    doLshiftadddivmodNoVar(scope, stmt.t) { x, y ->
                        makeDivModr(x, y)
                    } ?: return
                }
                is TvmArithmDivLshiftadddivmodVarInst -> {
                    doLshiftadddivmodVar(scope) { x, y ->
                        makeDivMod(x, y)
                    } ?: return
                }
                is TvmArithmDivLshiftadddivmodcVarInst -> {
                    doLshiftadddivmodVar(scope) { x, y ->
                        makeDivModc(x, y)
                    } ?: return
                }
                is TvmArithmDivLshiftadddivmodrVarInst -> {
                    doLshiftadddivmodVar(scope) { x, y ->
                        makeDivModr(x, y)
                    } ?: return
                }
                is TvmArithmDivMuldivInst -> {
                    doMuldivX(scope) { x, y ->
                        val div = makeDiv(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doMuldivX null
                        checkInBounds(div.value, scope)
                            ?: return@doMuldivX null
                        listOf(div.value)
                    } ?: return
                }
                is TvmArithmDivMuldivcInst -> {
                    doMuldivX(scope) { x, y ->
                        val div = makeDivc(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doMuldivX null
                        checkInBounds(div.value, scope)
                            ?: return@doMuldivX null
                        listOf(div.value)
                    } ?: return
                }
                is TvmArithmDivMuldivrInst -> {
                    doMuldivX(scope) { x, y ->
                        val div = makeDivr(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doMuldivX null
                        checkInBounds(div.value, scope)
                            ?: return@doMuldivX null
                        listOf(div.value)
                    } ?: return
                }
                is TvmArithmDivMuldivmodInst -> {
                    doMuldivX(scope) { x, y ->
                        val (div, mod) = makeDivMod(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doMuldivX null
                        checkInBounds(div.value, scope)
                            ?: return@doMuldivX null
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivMuldivmodcInst -> {
                    doMuldivX(scope) { x, y ->
                        val (div, mod) = makeDivModc(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doMuldivX null
                        checkInBounds(div.value, scope)
                            ?: return@doMuldivX null
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivMuldivmodrInst -> {
                    doMuldivX(scope) { x, y ->
                        val (div, mod) = makeDivModr(x, y)
                        checkOverflow(div.noOverflow, scope)
                            ?: return@doMuldivX null
                        checkInBounds(div.value, scope)
                            ?: return@doMuldivX null
                        listOf(div.value, mod)
                    } ?: return
                }
                is TvmArithmDivMulmodInst -> {
                    doMuldivX(scope) { x, y ->
                        val mod = makeMod(x, y)
                        // no checkOverflow or checkInBounds needed
                        listOf(mod)
                    } ?: return
                }
                is TvmArithmDivMulmodcInst -> {
                    doMuldivX(scope) { x, y ->
                        val mod = makeModc(x, y)
                        // no checkOverflow or checkInBounds needed
                        listOf(mod)
                    } ?: return
                }
                is TvmArithmDivMulmodrInst -> {
                    doMuldivX(scope) { x, y ->
                        val mod = makeModr(x, y)
                        // no checkOverflow or checkInBounds needed
                        listOf(mod)
                    } ?: return
                }

                // * >>
                is TvmArithmDivMulrshiftInst -> TODO()
                is TvmArithmDivMulrshiftcInst -> TODO()
                is TvmArithmDivMulrshiftrInst -> TODO()

                // * >>
                is TvmArithmDivMulrshiftVarInst -> TODO()
                is TvmArithmDivMulrshiftcVarInst -> TODO()
                is TvmArithmDivMulrshiftrVarInst -> TODO()

                // * + /%
                is TvmArithmDivMuladddivmodInst -> TODO()
                is TvmArithmDivMuladddivmodcInst -> TODO()
                is TvmArithmDivMuladddivmodrInst -> TODO()

                // * + >> %
                is TvmArithmDivMuladdrshiftmodInst -> TODO()
                is TvmArithmDivMuladdrshiftcmodInst -> TODO()
                is TvmArithmDivMuladdrshiftrmodInst -> TODO()

                // * % **2
                is TvmArithmDivMulmodpow2Inst -> TODO()
                is TvmArithmDivMulmodpow2cInst -> TODO()
                is TvmArithmDivMulmodpow2rInst -> TODO()

                // * % **2
                is TvmArithmDivMulmodpow2VarInst -> TODO()
                is TvmArithmDivMulmodpow2cVarInst -> TODO()
                is TvmArithmDivMulmodpow2rVarInst -> TODO()

                // * >> %
                is TvmArithmDivMulrshiftmodInst -> TODO()
                is TvmArithmDivMulrshiftcmodInst -> TODO()
                is TvmArithmDivMulrshiftrmodInst -> TODO()

                // * >> %
                is TvmArithmDivMulrshiftmodVarInst -> TODO()
                is TvmArithmDivMulrshiftcmodVarInst -> TODO()
                is TvmArithmDivMulrshiftrmodVarInst -> TODO()
            }

            scope.doWithState {
                newStmt(stmt.nextStmt())
            }
        }
    }

    private data class DivResult<S: UBvSort>(
        val value: UExpr<S>,
        val noOverflow: UBoolExpr
    )

    private fun <S: UBvSort> TvmContext.makeDivMod(x: UExpr<S>, y: UExpr<S>): Pair<DivResult<S>, UExpr<S>> =
        makeDiv(x, y) to makeMod(x, y)

    // shorter version, but unfortunately overflows in a test: mkBvSignedDivExpr(mkBvSubExpr(x, makeMod(x, y)), y)
    private fun <S: UBvSort> TvmContext.makeDiv(x: UExpr<S>, y: UExpr<S>): DivResult<S> {
        val zero = zeroValue.signExtendToSort(x.sort)
        val minusOne = minusOneValue.signExtendToSort(x.sort)

        val isNegative = mkBvSignedLessExpr(x, zero) xor mkBvSignedLessExpr(y, zero)
        val computedDiv = mkBvSignedDivExpr(x, y)
        val computedMod = mkBvSignedModExpr(x, y)
        val needToCorrect = isNegative and (computedMod neq zero)
        val noOverflow = mkBvDivNoOverflowExpr(x, y)  // only one case: MIN_VALUE / MINUS_ONE

        val result = mkIte(
            needToCorrect,
            trueBranch = { mkBvAddExpr(computedDiv, minusOne) },
            falseBranch = { computedDiv }
        )
        return DivResult(result, noOverflow)
    }

    // invariant: makeMod(x, y) == mkBvSubExpr(x, mkBvMulExpr(makeDiv(x, y), y))
    private fun <S: UBvSort> TvmContext.makeMod(x: UExpr<S>, y: UExpr<S>): UExpr<S> =
        mkBvSignedModExpr(x, y)

    private fun <S: UBvSort> TvmContext.makeDivModc(x: UExpr<S>, y: UExpr<S>): Pair<DivResult<S>, UExpr<S>> {
        val divc = makeDivc(x, y)
        val modc = mkBvSubExpr(x, mkBvMulExpr(y, divc.value))
        return divc to modc
    }

    private fun <S: UBvSort> TvmContext.makeDivc(x: UExpr<S>, y: UExpr<S>): DivResult<S> {
        val zero = zeroValue.signExtendToSort(x.sort)
        val one = oneValue.signExtendToSort(x.sort)

        val isPositive = mkBvSignedLessExpr(x, zero) eq mkBvSignedLessExpr(y, zero)
        val computedDiv = mkBvSignedDivExpr(x, y)
        val computedMod = mkBvSignedModExpr(x, y)
        val needToCorrect = isPositive and (computedMod neq zero)

        val value = mkIte(
            needToCorrect,
            trueBranch = { mkBvAddExpr(computedDiv, one) },
            falseBranch = { computedDiv }
        )
        return DivResult(value, mkBvDivNoOverflowExpr(x, y))
    }

    private fun <S: UBvSort> TvmContext.makeModc(x: UExpr<S>, y: UExpr<S>): UExpr<S> =
        makeDivModc(x, y).second

    private fun <S: UBvSort> TvmContext.makeDivModr(x: UExpr<S>, y: UExpr<S>): Pair<DivResult<S>, UExpr<S>> {
        val divr = makeDivr(x, y)
        val modr = mkBvSubExpr(x, mkBvMulExpr(y, divr.value))
        return divr to modr
    }

    private fun <S: UBvSort> TvmContext.makeDivr(x: UExpr<S>, y: UExpr<S>): DivResult<S> {
        val zero = zeroValue.signExtendToSort(x.sort)
        val two = twoValue.signExtendToSort(x.sort)

        val isYPositive = mkBvSignedGreaterExpr(y, zero)
        val absY = mkIte(
            isYPositive,
            trueBranch = { y },
            falseBranch = { mkBvNegationExpr(y) }
        )
        val computedMod = makeMod(x, absY)
        val halfMod = makeDivc(absY, two).value
        val chooseFloor = isYPositive xor mkBvSignedGreaterOrEqualExpr(computedMod, halfMod)

        val value = mkIte(
            chooseFloor,
            trueBranch = { makeDiv(x, y).value },  // floor
            falseBranch = { makeDivc(x, y).value }  // ceil
        )
        return DivResult(value, mkBvDivNoOverflowExpr(x, y))
    }

    private fun <S: UBvSort> TvmContext.makeModr(x: UExpr<S>, y: UExpr<S>): UExpr<S> =
        makeDivModr(x, y).second

    private fun checkDivisionByZero(expr: UExpr<TvmInt257Sort>, scope: TvmStepScope) = with(ctx) {
        val neqZero = mkEq(expr, zeroValue).not()
        scope.fork(
            neqZero,
            blockOnFalseState = setFailure(TvmIntegerOverflow)
        )
    }

    private val min257BitValue = with(ctx) { bvMinValueSignedExtended(intBitsValue) }
    private val max257BitValue = with(ctx) { bvMaxValueSignedExtended(intBitsValue) }

    /**
     * Checks whether N-bit (N > 257) signed integer fits in range -2^256..(2^256 - 1).
     * If not, sets TvmIntegerOverflow.
     */
    private fun <S: UBvSort> checkInBounds(expr: UExpr<S>, scope: TvmStepScope) = with(ctx) {
        val minValue = min257BitValue.signExtendToSort(expr.sort)
        val maxValue = max257BitValue.signExtendToSort(expr.sort)
        val inBounds = mkBvSignedLessOrEqualExpr(minValue, expr) and mkBvSignedLessOrEqualExpr(expr, maxValue)
        scope.fork(
            inBounds,
            blockOnFalseState = setFailure(TvmIntegerOverflow)
        )
    }

    private fun checkOverflow(noOverflowExpr: UBoolExpr, scope: TvmStepScope): Unit? = scope.fork(
        noOverflowExpr,
        blockOnFalseState = setFailure(TvmIntegerOverflow)
    )

    private fun checkInRange(expr: UExpr<TvmInt257Sort>, scope: TvmStepScope, min: Int, max: Int) = with(ctx) {
        val cond = mkBvSignedLessOrEqualExpr(min.toBv257(), expr) and mkBvSignedLessOrEqualExpr(expr, max.toBv257())
        scope.fork(
            cond,
            blockOnFalseState = setFailure(TvmIntegerOutOfRange)
        )
    }

    private fun takeOperandsAndCheckForZero(scope: TvmStepScope): Pair<UExpr<TvmInt257Sort>, UExpr<TvmInt257Sort>>? {
        val (secondOperand, firstOperand) = scope.calcOnState {
            stack.takeLastInt() to stack.takeLastInt()
        }
        checkDivisionByZero(secondOperand, scope) ?: return null
        return firstOperand to secondOperand
    }

    private fun doAdddivmodX(
        scope: TvmStepScope,
        makeDivmodX: (UExpr<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>) -> Pair<DivResult<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>>
    ): Unit? = with(ctx) {
        val z = scope.calcOnState { stack.takeLastInt() }
        val w = scope.calcOnState { stack.takeLastInt() }
        val x = scope.calcOnState { stack.takeLastInt() }
        checkDivisionByZero(z, scope) ?: return null

        val xExtended = x.signedExtendByOne()
        val wExtended = w.signedExtendByOne()
        val zExtended = z.signedExtendByOne()
        val addExtended = mkBvAddExpr(xExtended, wExtended)
        val (divExtended, modExtended) = makeDivmodX(addExtended, zExtended)

        checkOverflow(divExtended.noOverflow, scope) ?: return null
        checkInBounds(divExtended.value, scope) ?: return null

        val div = divExtended.value.extractToInt257Sort()
        val mod = modExtended.extractToInt257Sort()
        scope.calcOnState {
            stack.add(div, TvmIntegerType)
            stack.add(mod, TvmIntegerType)
        }
    }

    private fun doRshiftXNoVar(
        stmtT: Int,
        scope: TvmStepScope,
        makeModOrDivX: (UExpr<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>) -> List<UExpr<TvmInt257Ext1Sort>>
    ): Unit = with(ctx) {
        val t = (stmtT + 1).toBv257()
        val x = scope.calcOnState { stack.takeLastInt() }
        doRshiftX(x, t, scope, makeModOrDivX)
    }

    private fun doRshiftXVar(
        scope: TvmStepScope,
        makeModOrDivX: (UExpr<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>) -> List<UExpr<TvmInt257Ext1Sort>>
    ): Unit? = with(ctx) {
        val t = scope.calcOnState { stack.takeLastInt() }
        checkInRange(t, scope, min = 0, max = 256) ?: return null

        val x = scope.calcOnState { stack.takeLastInt() }
        doRshiftX(x, t, scope, makeModOrDivX)
    }

    private fun doRshiftX(
        x: UExpr<TvmInt257Sort>,
        t: UExpr<TvmInt257Sort>,
        scope: TvmStepScope,
        makeModOrDivX: (UExpr<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>) -> List<UExpr<TvmInt257Ext1Sort>>
    ): Unit = with(ctx) {
        val xExtended = x.signedExtendByOne()
        val yExtended = mkBvShiftLeftExpr(oneValue.signedExtendByOne(), t.signedExtendByOne())

        // overflow cannot happen (y > 0)
        // checkInBounds is not needed: overflow cannot happen (y > 0, x is in bounds)
        val resultExtended = makeModOrDivX(xExtended, yExtended)
        resultExtended.forEach {
            val result = it.extractToInt257Sort()
            scope.doWithState {
                stack.add(result, TvmIntegerType)
            }
        }
    }

    private fun doModpow2XNoVar(
        scope: TvmStepScope,
        stmtT: Int,
        makeModX: (UExpr<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>) -> UExpr<TvmInt257Ext1Sort>
    ) = with(ctx) {
        val x = scope.calcOnState { stack.takeLastInt() }
        doModpow2X(scope, x, (stmtT + 1).toBv257(), makeModX)
    }

    private fun doModpow2XVar(
        scope: TvmStepScope,
        makeModX: (UExpr<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>) -> UExpr<TvmInt257Ext1Sort>
    ): Unit? = with(ctx) {
        val t = scope.calcOnState { stack.takeLastInt() }
        checkInRange(t, scope, min = 0, max = 256) ?: return null

        val x = scope.calcOnState { stack.takeLastInt() }
        doModpow2X(scope, x, t, makeModX)
    }

    private fun doModpow2X(
        scope: TvmStepScope,
        x: UExpr<TvmInt257Sort>,
        t: UExpr<TvmInt257Sort>,
        makeModX: (UExpr<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>) -> UExpr<TvmInt257Ext1Sort>
    ) = with(ctx) {
        val divExtended = mkBvShiftLeftExpr(oneValue.signedExtendByOne(), t.signedExtendByOne())
        val xExtended = x.signedExtendByOne()
        val resultExtended = makeModX(xExtended, divExtended)

        // no need for checkInBounds: overflow cannot happen
        val result = resultExtended.extractToInt257Sort()
        scope.calcOnState { stack.add(result, TvmIntegerType) }
    }

    private fun doLShiftXNoVar(
        scope: TvmStepScope,
        stmtT: Int,
        makeDivOrMod: (UExpr<TvmInt257Ext256Sort>, UExpr<TvmInt257Ext256Sort>) -> List<UExpr<TvmInt257Ext256Sort>>?
    ) = with(ctx) {
        val y = scope.calcOnState { stack.takeLastInt() }
        val x = scope.calcOnState { stack.takeLastInt() }
        val t = (stmtT + 1).toBv257()
        doLshiftX(scope, x, y, t, makeDivOrMod)
    }

    private fun doLShiftXVar(
        scope: TvmStepScope,
        makeDivOrMod: (UExpr<TvmInt257Ext256Sort>, UExpr<TvmInt257Ext256Sort>) -> List<UExpr<TvmInt257Ext256Sort>>?
    ) = with(ctx) {
        val t = scope.calcOnState { stack.takeLastInt() }
        checkInRange(t, scope, min = 0, max = 256) ?: return null

        val y = scope.calcOnState { stack.takeLastInt() }
        val x = scope.calcOnState { stack.takeLastInt() }
        doLshiftX(scope, x, y, t, makeDivOrMod)
    }

    private fun doLshiftX(
        scope: TvmStepScope,
        x: UExpr<TvmInt257Sort>,
        y: UExpr<TvmInt257Sort>,
        t: UExpr<TvmInt257Sort>,
        makeDivOrMod: (UExpr<TvmInt257Ext256Sort>, UExpr<TvmInt257Ext256Sort>) -> List<UExpr<TvmInt257Ext256Sort>>?
    ): Unit? = with(ctx) {
        checkDivisionByZero(y, scope) ?: return null

        val xExtended = x.signedExtendBy256()
        val xShifted = mkBvShiftLeftExpr(xExtended, t.signedExtendBy256())
        val yExtended = y.signedExtendBy256()
        val resultsExtended = makeDivOrMod(xShifted, yExtended) ?: return null

        val results = resultsExtended.map { it.extractToInt257Sort() }
        results.forEach { result -> scope.calcOnState { stack.add(result, TvmIntegerType) } }
    }

    private fun doAddrshiftmodXNoVar(
        scope: TvmStepScope,
        stmtT: Int,
        makeDivModX: (UExpr<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>) -> Pair<DivResult<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>>
    ) = with(ctx) {
        val w = scope.calcOnState { stack.takeLastInt() }
        val x = scope.calcOnState { stack.takeLastInt() }
        doAddrshiftmodX(scope, x, w, (stmtT + 1).toBv257(), makeDivModX)
    }

    private fun doAddrshiftmodXVar(
        scope: TvmStepScope,
        makeDivModX: (UExpr<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>) -> Pair<DivResult<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>>
    ) = with(ctx) {
        val t = scope.calcOnState { stack.takeLastInt() }
        checkInRange(t, scope, min = 0, max = 256) ?: return null

        val w = scope.calcOnState { stack.takeLastInt() }
        val x = scope.calcOnState { stack.takeLastInt() }
        doAddrshiftmodX(scope, x, w, t, makeDivModX)
    }

    private fun doAddrshiftmodX(
        scope: TvmStepScope,
        x: UExpr<TvmInt257Sort>,
        w: UExpr<TvmInt257Sort>,
        t: UExpr<TvmInt257Sort>,
        makeDivModX: (UExpr<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>) -> Pair<DivResult<TvmInt257Ext1Sort>, UExpr<TvmInt257Ext1Sort>>
    ): Unit? = with(ctx) {
        val wExtended = w.signedExtendByOne()
        val xExtended = x.signedExtendByOne()
        val sumExtended = mkBvAddExpr(xExtended, wExtended)
        val yExtended = mkBvShiftLeftExpr(oneValue.signedExtendByOne(), t.signedExtendByOne())
        val (divExtended, modExtended) = makeDivModX(sumExtended, yExtended)

        // no need for checkOverflow (y > 0)
        checkInBounds(divExtended.value, scope) ?: return null

        val div = divExtended.value.extractToInt257Sort()
        val mod = modExtended.extractToInt257Sort()
        scope.doWithState {
            stack.add(div, TvmIntegerType)
            stack.add(mod, TvmIntegerType)
        }
    }

    private fun doLshiftadddivmodNoVar(
        scope: TvmStepScope,
        stmtT: Int,
        makeDivModX: (UExpr<TvmInt257Ext256Sort>, UExpr<TvmInt257Ext256Sort>) -> Pair<DivResult<TvmInt257Ext256Sort>, UExpr<TvmInt257Ext256Sort>>
    ) = with(ctx) {
        val z = scope.calcOnState { stack.takeLastInt() }
        val w = scope.calcOnState { stack.takeLastInt() }
        val x = scope.calcOnState { stack.takeLastInt() }
        doLshiftadddivmod(scope, x, w, z, (stmtT + 1).toBv257(), makeDivModX)
    }

    private fun doLshiftadddivmodVar(
        scope: TvmStepScope,
        makeDivModX: (UExpr<TvmInt257Ext256Sort>, UExpr<TvmInt257Ext256Sort>) -> Pair<DivResult<TvmInt257Ext256Sort>, UExpr<TvmInt257Ext256Sort>>
    ) = with(ctx) {
        val t = scope.calcOnState { stack.takeLastInt() }
        checkInRange(t, scope, min = 0, max = 256) ?: return null

        val z = scope.calcOnState { stack.takeLastInt() }
        val w = scope.calcOnState { stack.takeLastInt() }
        val x = scope.calcOnState { stack.takeLastInt() }
        doLshiftadddivmod(scope, x, w, z, t, makeDivModX)
    }

    private fun doLshiftadddivmod(
        scope: TvmStepScope,
        x: UExpr<TvmInt257Sort>,
        w: UExpr<TvmInt257Sort>,
        z: UExpr<TvmInt257Sort>,
        t: UExpr<TvmInt257Sort>,
        makeDivModX: (UExpr<TvmInt257Ext256Sort>, UExpr<TvmInt257Ext256Sort>) -> Pair<DivResult<TvmInt257Ext256Sort>, UExpr<TvmInt257Ext256Sort>>
    ): Unit? = with(ctx) {
        checkDivisionByZero(z, scope) ?: return null

        val xExtended = x.signedExtendBy256()
        val xShifted = mkBvShiftLeftExpr(xExtended, t.signedExtendBy256())
        val wExtended = w.signedExtendBy256()
        val sumExtended = mkBvAddExpr(xShifted, wExtended)
        val zExtended = z.signedExtendBy256()
        val (divExtended, modExtended) = makeDivModX(sumExtended, zExtended)

        checkOverflow(divExtended.noOverflow, scope) ?: return null
        checkInBounds(divExtended.value, scope) ?: return null

        val div = divExtended.value.extractToInt257Sort()
        val mod = modExtended.extractToInt257Sort()
        scope.doWithState {
            stack.add(div, TvmIntegerType)
            stack.add(mod, TvmIntegerType)
        }
    }

    private fun doMuldivX(
        scope: TvmStepScope,
        makeDivOrMod: (UExpr<TvmInt257Ext256Sort>, UExpr<TvmInt257Ext256Sort>) -> List<UExpr<TvmInt257Ext256Sort>>?
    ): Unit? = with(ctx) {
        val z = scope.calcOnState { stack.takeLastInt() }
        val y = scope.calcOnState { stack.takeLastInt() }
        val x = scope.calcOnState { stack.takeLastInt() }
        checkDivisionByZero(z, scope) ?: return null

        val xExtended = x.signedExtendBy256()
        val yExtended = y.signedExtendBy256()
        val zExtended = z.signedExtendBy256()
        val mulExtended = mkBvMulExpr(xExtended, yExtended)
        val resultsExtended = makeDivOrMod(mulExtended, zExtended) ?: return null

        val results = resultsExtended.map { it.extractToInt257Sort() }
        scope.doWithState {
            results.forEach { stack.add(it, TvmIntegerType) }
        }
    }

    private fun UExpr<TvmInt257Sort>.signedExtendByOne(): UExpr<TvmInt257Ext1Sort> =
        with(this@TvmArithDivInterpreter.ctx) {
            this@signedExtendByOne.signExtendToSort(int257Ext1Sort)
        }

    private fun UExpr<TvmInt257Sort>.signedExtendBy256(): UExpr<TvmInt257Ext256Sort> =
        with(this@TvmArithDivInterpreter.ctx) {
            this@signedExtendBy256.signExtendToSort(int257Ext256Sort)
        }
}
