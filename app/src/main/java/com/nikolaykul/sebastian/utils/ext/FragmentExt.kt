package com.nikolaykul.sebastian.utils.ext

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import com.nikolaykul.sebastian.utils.vm.ViewModelFragmentFactoryDelegate
import com.nikolaykul.sebastian.utils.vm.ViewModelFragmentFactoryProviderDelegate

inline fun <reified T : ViewModel> Fragment.viewModelFactoryDelegate(
    noinline factory: () -> ViewModel
) = ViewModelFragmentFactoryDelegate(this, factory, T::class.java)


inline fun <reified T : ViewModel> Fragment.viewModelFactoryProviderDelegate(
    noinline factoryProvider: () -> ViewModelProvider.Factory
) = ViewModelFragmentFactoryProviderDelegate(this, factoryProvider, T::class.java)
