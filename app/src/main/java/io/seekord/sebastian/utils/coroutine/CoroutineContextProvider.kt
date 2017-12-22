@file:Suppress("PropertyName")

package io.seekord.sebastian.utils.coroutine

import android.support.annotation.VisibleForTesting
import kotlinx.coroutines.experimental.CommonPool
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.EmptyCoroutineContext

/**
 * Created by nikolay
 */

object CoroutineContextProvider : ContextProvider {
    private var currentContextProvider: ContextProvider = DefaultContextProvider()
    override val UI by lazy { currentContextProvider.UI }
    override val IO = currentContextProvider.IO

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun mockContexts() {
        currentContextProvider = MockContextProvider()
    }

}

private interface ContextProvider {
    val UI: CoroutineContext
    val IO: CoroutineContext
}

private class DefaultContextProvider : ContextProvider {
    override val UI by lazy { kotlinx.coroutines.experimental.android.UI }

    // See: https://github.com/Kotlin/kotlinx.coroutines/issues/79
    // Change later on a real IO Handler Context implementation
    override val IO = CommonPool

}

private class MockContextProvider : ContextProvider {
    override val UI = EmptyCoroutineContext
    override val IO = EmptyCoroutineContext
}
