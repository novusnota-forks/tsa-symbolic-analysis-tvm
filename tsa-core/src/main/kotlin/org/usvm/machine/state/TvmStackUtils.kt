package org.usvm.machine.state

import org.ton.bytecode.TvmBuilderType
import org.ton.bytecode.TvmCellType
import org.ton.bytecode.TvmContinuationType
import org.ton.bytecode.TvmContinuationValue
import org.ton.bytecode.TvmIntegerType
import org.ton.bytecode.TvmReferenceType
import org.ton.bytecode.TvmSliceType
import org.ton.bytecode.TvmTupleType
import org.usvm.UBvSort
import org.usvm.UExpr
import org.usvm.UHeapRef

fun TvmStack.takeLastInt(): UExpr<UBvSort> {
    val intStackValue = takeLast(TvmIntegerType) { id ->
        ctx.mkRegisterReading(id, ctx.int257sort)
    }

    return intStackValue.intValue
}

context(TvmState)
fun TvmStack.takeLastCell(): UHeapRef =
    takeLastRef(this, TvmCellType, TvmStack.TvmStackValue::cellValue) {
        generateSymbolicCell()
    }

context(TvmState)
fun TvmStack.takeLastSlice(): UHeapRef =
    takeLastRef(this, TvmSliceType, TvmStack.TvmStackValue::sliceValue) {
        generateSymbolicSlice()
    }

context(TvmState)
fun TvmStack.takeLastBuilder(): UHeapRef =
    takeLastRef(this, TvmBuilderType, TvmStack.TvmStackValue::builderValue) {
        generateSymbolicBuilder()
    }

context(TvmState)
fun TvmStack.takeLastTuple(): UHeapRef =
    takeLastRef(this, TvmTupleType, TvmStack.TvmStackValue::tupleValue) {
        generateSymbolicTuple()
    }

fun TvmStack.takeLastContinuation(): TvmContinuationValue {
    val continuationStackValue = takeLast(TvmContinuationType) { _ ->
        error("Unexpected continuation as an input")
    }

    return continuationStackValue.continuationValue
}

context(TvmState)
private fun takeLastRef(
    stack: TvmStack,
    referenceType: TvmReferenceType,
    extractValue: TvmStack.TvmStackValue.() -> UHeapRef,
    generateSymbolicRef: (Int) -> UHeapRef
): UHeapRef {
    val lastRefValue = stack.takeLast(referenceType, generateSymbolicRef)
    return lastRefValue.extractValue()
}
