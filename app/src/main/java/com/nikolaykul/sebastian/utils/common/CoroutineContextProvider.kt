@file:Suppress("PropertyName")

package com.nikolaykul.sebastian.utils.common

import android.support.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

// TODO: rename interfaces to match `DispatcherProvider`

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
    val UI: CoroutineDispatcher
    val IO: CoroutineDispatcher
    val COMPUTATION: CoroutineDispatcher
}


private class DefaultContextProvider : ContextProvider {
    override val UI by lazy { Dispatchers.Main }
    override val IO by lazy { Dispatchers.IO }
    override val COMPUTATION by lazy { Dispatchers.Default }
}


private class MockContextProvider : ContextProvider {
    override val UI = Dispatchers.Unconfined
    override val IO = Dispatchers.Unconfined
    override val COMPUTATION = Dispatchers.Unconfined
}
