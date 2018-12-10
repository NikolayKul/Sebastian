@file:Suppress("UNCHECKED_CAST")

package com.nikolaykul.sebastian.utils.vm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Reusable
import javax.inject.Inject
import javax.inject.Provider

@Reusable
class ViewModelCommonFactory @Inject constructor(
    private val providers: Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(clazz: Class<T>): T {
        val provider = providers[clazz]
            ?: throw IllegalArgumentException("Unknown ViewModel class: $clazz")
        return provider.get() as T
    }
}


class ViewModelWrapperFactory(private val factory: () -> ViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(clazz: Class<T>): T {
        return factory() as? T
            ?: throw IllegalArgumentException("Unknown ViewModel class: $clazz")
    }
}
