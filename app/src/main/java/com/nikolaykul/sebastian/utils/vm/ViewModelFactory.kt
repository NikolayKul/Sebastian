package com.nikolaykul.sebastian.utils.vm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val providers: Map<ProviderKey, ProviderValue>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(clazz: Class<T>): T {
        val provider = providers[clazz]
                ?: throw IllegalArgumentException("Unknown ViewModel class: $clazz")
        return provider.get() as T
    }

}


typealias ProviderKey = Class<out ViewModel>
typealias ProviderValue = @JvmSuppressWildcards Provider<ViewModel>
