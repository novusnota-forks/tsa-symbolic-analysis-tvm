package org.usvm.machine.types

import org.ton.TvmDataCellStructure
import org.usvm.UBoolExpr
import org.usvm.UConcreteHeapRef
import org.usvm.UExpr
import org.usvm.api.readField
import org.usvm.machine.TvmContext.Companion.cellDataField
import org.usvm.machine.TvmSizeSort
import org.usvm.machine.state.TvmState
import org.usvm.machine.state.readCellRef
import org.usvm.mkSizeAddExpr
import org.usvm.mkSizeExpr

class TvmDataCellInfoTree private constructor(
    val address: UConcreteHeapRef,
    private val root: Vertex,
) {
    fun <Acc> fold(init: Acc, f: (Acc, Vertex) -> Acc): Acc =
        root.fold(init, f)

    class Vertex(
        val guard: UBoolExpr,
        val structure: TvmDataCellStructure,
        val prefixSize: UExpr<TvmSizeSort>,
        val refNumber: Int,
        private val children: List<Vertex>,
    ) {
        internal fun <Acc> fold(init: Acc, f: (Acc, Vertex) -> Acc): Acc {
            return children.fold(f(init, this)) { acc, child -> child.fold(acc, f) }
        }
    }

    companion object {
        fun construct(
            state: TvmState,
            structure: TvmDataCellStructure,
            address: UConcreteHeapRef,
            guard: UBoolExpr = state.ctx.trueExpr,
        ): List<TvmDataCellInfoTree> {
            val (root, other) = constructVertex(
                state,
                structure,
                guard,
                address,
                prefixSize = state.ctx.zeroSizeExpr,
                refNumber = 0,
            )
            val result = TvmDataCellInfoTree(address, root)
            return listOf(result) + other
        }

        private fun constructVertex(
            state: TvmState,
            structure: TvmDataCellStructure,
            guard: UBoolExpr,
            address: UConcreteHeapRef,
            prefixSize: UExpr<TvmSizeSort>,
            refNumber: Int,
        ): Pair<Vertex, List<TvmDataCellInfoTree>> = with(state.ctx) {
            when (structure) {
                is TvmDataCellStructure.Empty, TvmDataCellStructure.Unknown -> {
                    Vertex(guard, structure, prefixSize, refNumber, emptyList()) to emptyList()
                }
                is TvmDataCellStructure.KnownTypePrefix -> {
                    val (child, other) = constructVertex(
                        state,
                        structure.rest,
                        guard,
                        address,
                        mkSizeAddExpr(prefixSize, structure.typeOfPrefix.offset(state, address, prefixSize)),
                        refNumber,
                    )
                    Vertex(guard, structure, prefixSize, refNumber, listOf(child)) to other
                }
                is TvmDataCellStructure.SwitchPrefix -> {
                    val other = mutableListOf<TvmDataCellInfoTree>()
                    val cellContent = state.memory.readField(address, cellDataField, cellDataSort)
                    val prefix = mkBvExtractExpr(high = structure.switchSize - 1, low = 0, cellContent)
                    val children = structure.variants.entries.map { (key, selfRestVariant) ->
                        val expectedPrefix = mkBv(key, structure.switchSize.toUInt())
                        val prefixGuard = prefix eq expectedPrefix
                        val newGuard = guard and prefixGuard
                        val (child, childOther) = constructVertex(
                            state,
                            selfRestVariant,
                            newGuard,
                            address,
                            mkSizeAddExpr(prefixSize, mkSizeExpr(structure.switchSize)),
                            refNumber,
                        )
                        other += childOther
                        child
                    }
                    Vertex(guard, structure, prefixSize, refNumber, children) to other
                }
                is TvmDataCellStructure.LoadRef -> {
                    val refAddress = state.readCellRef(address, mkSizeExpr(refNumber)) as UConcreteHeapRef
                    val other = construct(state, structure.ref, refAddress, guard)
                    val (child, childOther) = constructVertex(
                        state,
                        structure.selfRest,
                        guard,
                        address,
                        prefixSize,
                        refNumber + 1,
                    )
                    Vertex(guard, structure, prefixSize, refNumber, listOf(child)) to (childOther + other)
                }
            }
        }
    }
}
