package com.nikolaykul.sebastian.utils.vm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


/**
 * Alias for [Map.Entry.key] used by [ViewModelFactory.providers]
 */
typealias ProviderKey = Class<out ViewModel>


/**
 * Alias for [Map.Entry.value] used by [ViewModelFactory.providers]
 */
typealias ProviderValue = @JvmSuppressWildcards Provider<ViewModel>


@Singleton
class ViewModelFactory @Inject constructor(
        private val providers: Map<ProviderKey, ProviderValue>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(clazz: Class<T>): T {
        val provider = providers[clazz] ?: findProviderOrThrow(clazz)
        return provider.get() as T
    }

    // can't reify T because it's not known at the compile time. So fall back to the Class<T>
    private fun <T : ViewModel> findProviderOrThrow(clazz: Class<T>): Provider<ViewModel> {
        val provider = providers.entries
                .firstOrNull { clazz.isAssignableFrom(it.key) }
                ?.value
        return provider ?: throw IllegalArgumentException("Unknown ViewModel class $clazz")
    }

}
