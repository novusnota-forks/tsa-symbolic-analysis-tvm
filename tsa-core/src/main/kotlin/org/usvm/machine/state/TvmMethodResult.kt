package org.usvm.machine.state

import kotlinx.serialization.Serializable
import org.usvm.machine.state.TvmMethodResult.*
import org.ton.bytecode.TvmCodeBlock
import org.usvm.UBv32Sort
import org.usvm.UExpr

/**
 * Represents a result of a method invocation.
 */
sealed interface TvmMethodResult {
    /**
     * No call was performed.
     */
    data object NoCall : TvmMethodResult

    /**
     * A [method] successfully returned.
     */
    class TvmSuccess(
        val method: TvmCodeBlock,
        val stack: TvmStack,
    ) : TvmMethodResult

    /**
     * A method exited with non-successful exit code.
     */
    @Serializable
    sealed interface TvmFailure : TvmMethodResult {
        val exitCode: UInt
        val ruleName: String
    }
}

// TODO standard exit code should be placed in codepage 0?
// TODO add integer underflow?
@Serializable
object TvmIntegerOverflowError : TvmFailure {
    override val exitCode: UInt = 4u
    override val ruleName: String = "integer-overflow"

    override fun toString(): String = "TVM integer overflow, exit code: $exitCode"
}

@Serializable
object TvmIntegerOutOfRangeError : TvmFailure {
    override val exitCode: UInt = 5u
    override val ruleName: String = "integer-out-of-range"

    override fun toString(): String = "TVM integer out of expected range, exit code: $exitCode" // TODO add expected range to the message?
}

// TODO add expected type
@Serializable
object TvmTypeCheckError : TvmFailure {
    override val exitCode: UInt = 7u
    override val ruleName: String = "wrong-type"

    override fun toString(): String = "TVM type check error, exit code: $exitCode"
}

@Serializable
object TvmCellOverflowError : TvmFailure {
    override val exitCode: UInt = 8u
    override val ruleName: String = "cell-overflow"

    override fun toString(): String = "TVM cell overflow, exit code: $exitCode"
}

@Serializable
object TvmCellUnderflowError : TvmFailure {
    override val exitCode: UInt = 9u
    override val ruleName: String = "cell-underflow"

    override fun toString(): String = "TVM cell underflow, exit code: $exitCode"
}

data class TvmOutOfGas(val consumedGas: UExpr<UBv32Sort>, val gasLimit: UExpr<UBv32Sort>) : TvmFailure {
    override val exitCode: UInt = 13u
    override val ruleName: String = "out-of-gas"

    override fun toString(): String =
        "TVM out of gas error (exit code: $exitCode): gas consumed: $consumedGas, limit: $gasLimit"
}

@Serializable
data class TvmUnknownFailure(override val exitCode: UInt): TvmFailure {
    override val ruleName: String = "user-defined-error"

    override fun toString(): String = "TVM user defined error with exit code $exitCode"
}

// TODO add remaining
