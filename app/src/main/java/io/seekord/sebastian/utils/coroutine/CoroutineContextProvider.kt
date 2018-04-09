@file:Suppress("PropertyName")

package io.seekord.sebastian.utils.coroutine

import android.support.annotation.VisibleForTesting
import kotlinx.coroutines.experimental.CommonPool
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.EmptyCoroutineContext

/**
 * @author NikolayKul
 */

object CoroutineContextProvider : ContextProvider {
    private var provider: ContextProvider = DefaultContextProvider()
    override val UI by lazy { provider.UI }
    override val IO by lazy { provider.IO }
    override val COMPUTATION by lazy { provider.COMPUTATION }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun mockContexts() {
        provider = MockContextProvider()
    }

}


private interface ContextProvider {
    val UI: CoroutineContext
    val IO: CoroutineContext
    val COMPUTATION: CoroutineContext
}


private class DefaultContextProvider : ContextProvider {
    override val UI = kotlinx.coroutines.experimental.android.UI

    // See: https://github.com/Kotlin/kotlinx.coroutines/issues/79
    // Change later on a real IO Handler Context implementation
    override val IO = CommonPool

    override val COMPUTATION = CommonPool
}


private class MockContextProvider : ContextProvider {
    override val UI = EmptyCoroutineContext
    override val IO = EmptyCoroutineContext
    override val COMPUTATION = EmptyCoroutineContext
}
