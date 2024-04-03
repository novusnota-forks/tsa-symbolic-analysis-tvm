package org.ton.examples.stack

import org.ton.bytecode.TvmIntegerType
import org.ton.examples.compareMethodStateStack
import org.ton.examples.loadIntegers
import org.usvm.machine.TvmComponents
import org.usvm.machine.TvmContext
import org.usvm.machine.compileAndAnalyzeFift
import org.usvm.machine.runFiftMethod
import org.usvm.machine.state.TvmStack
import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class StackComplexOperationsTest {
    private val ctx = TvmContext(TvmComponents())

    private val stackComplexFiftPath: String = "/stack/StackComplex.fif"
    private val stackNullChecksFiftPath: String = "/stack/NullChecks.fif"

    @Test
    fun testStackReverse(): Unit = with(ctx) {
        val stack = TvmStack(ctx)

        val originalOrder = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val reversed53Order = listOf(0, 1, 2, 7, 6, 5, 4, 3, 8, 9, 10)

        originalOrder.forEach { stack.add(it.toBv257(), TvmIntegerType) }

        stack.reverse(5, 3)

        val stackState = stack.loadIntegers(originalOrder.size)
        assertEquals(reversed53Order, stackState)
    }

    @Test
    fun testStackNullChecks(): Unit = with(ctx) {
        val fiftResourcePath = this::class.java.getResource(stackNullChecksFiftPath)?.path?.let { Path(it) }
            ?: error("Cannot find resource fift $stackNullChecksFiftPath")

        val methodStates = compileAndAnalyzeFift(fiftResourcePath)

        val methodIds = (0..15).toSet()
        compareMethodStateStack(methodIds, methodStates) { method ->
            runFiftMethod(fiftResourcePath, method.id)
        }
    }

    @Test
    fun testStackComplexFift(): Unit = with(ctx) {
        val fiftResourcePath = this::class.java.getResource(stackComplexFiftPath)?.path?.let { Path(it) }
            ?: error("Cannot find resource fift $stackComplexFiftPath")

        val methodStates = compileAndAnalyzeFift(fiftResourcePath)

        val methodIds = (0..30).toSet()
        compareMethodStateStack(methodIds, methodStates) { method ->
            runFiftMethod(fiftResourcePath, method.id)
        }
    }
}
