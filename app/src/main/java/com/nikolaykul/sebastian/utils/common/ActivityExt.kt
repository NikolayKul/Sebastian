package com.nikolaykul.sebastian.utils.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.FragmentActivity
import com.nikolaykul.sebastian.utils.vm.ViewModelActivityFactoryDelegate
import com.nikolaykul.sebastian.utils.vm.ViewModelActivityFactoryProviderDelegate

inline fun <reified T : ViewModel> FragmentActivity.viewModelFactoryDelegate(
    noinline viewModelProvider: () -> ViewModel
) = ViewModelActivityFactoryDelegate(this, viewModelProvider, T::class.java)


inline fun <reified T : ViewModel> FragmentActivity.viewModelFactoryProviderDelegate(
    noinline viewModelFactoryProvider: () -> ViewModelProvider.Factory
) = ViewModelActivityFactoryProviderDelegate(this, viewModelFactoryProvider, T::class.java)
