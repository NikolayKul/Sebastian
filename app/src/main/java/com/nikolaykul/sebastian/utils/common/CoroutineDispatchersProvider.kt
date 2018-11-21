@file:Suppress("PropertyName")

package com.nikolaykul.sebastian.utils.common

import android.support.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

object CoroutineDispatchersProvider : DispatchersProvider {
    private var provider: DispatchersProvider = DefaultDispatchersProvider()

    override val MAIN by lazy { provider.MAIN }
    override val IO by lazy { provider.IO }
    override val DEFAULT by lazy { provider.DEFAULT }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun mockDispatchers() {
        provider = MockDispatchersProvider()
    }
}


private class DefaultDispatchersProvider : DispatchersProvider {
    override val MAIN by lazy { Dispatchers.Main }
    override val IO by lazy { Dispatchers.IO }
    override val DEFAULT by lazy { Dispatchers.Default }
}


@ExperimentalCoroutinesApi
private class MockDispatchersProvider : DispatchersProvider {
    override val MAIN = Dispatchers.Unconfined
    override val IO = Dispatchers.Unconfined
    override val DEFAULT = Dispatchers.Unconfined
}


private interface DispatchersProvider {
    val MAIN: CoroutineDispatcher
    val IO: CoroutineDispatcher
    val DEFAULT: CoroutineDispatcher
}
