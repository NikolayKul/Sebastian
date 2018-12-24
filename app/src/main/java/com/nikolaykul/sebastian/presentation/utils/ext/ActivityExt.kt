package com.nikolaykul.sebastian.presentation.utils.ext

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.FragmentActivity
import com.nikolaykul.sebastian.presentation.utils.vm.ViewModelActivityFactoryDelegate
import com.nikolaykul.sebastian.presentation.utils.vm.ViewModelActivityFactoryProviderDelegate

inline fun <reified T : ViewModel> FragmentActivity.viewModelFactoryDelegate(
    noinline factory: () -> ViewModel
) = ViewModelActivityFactoryDelegate(this, factory, T::class.java)


inline fun <reified T : ViewModel> FragmentActivity.viewModelFactoryProviderDelegate(
    noinline factoryProvider: () -> ViewModelProvider.Factory
) = ViewModelActivityFactoryProviderDelegate(this, factoryProvider, T::class.java)