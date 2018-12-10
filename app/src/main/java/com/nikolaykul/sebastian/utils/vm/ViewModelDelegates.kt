package com.nikolaykul.sebastian.utils.vm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import kotlin.reflect.KProperty


class ViewModelActivityFactoryDelegate<T : ViewModel>(
    private val activity: FragmentActivity,
    private val factory: () -> ViewModel,
    clazz: Class<T>
) : BaseViewModelDelegate<T>(clazz) {
    override fun getViewModelProvider(): ViewModelProvider =
        ViewModelProviders.of(activity, ViewModelWrapperFactory(factory))
}


class ViewModelActivityFactoryProviderDelegate<T : ViewModel>(
    private val activity: FragmentActivity,
    private val factoryProvider: () -> ViewModelProvider.Factory,
    clazz: Class<T>
) : BaseViewModelDelegate<T>(clazz) {
    override fun getViewModelProvider(): ViewModelProvider =
        ViewModelProviders.of(activity, factoryProvider())
}


class ViewModelFragmentFactoryDelegate<T : ViewModel>(
    private val fragment: Fragment,
    private val factory: () -> ViewModel,
    clazz: Class<T>
) : BaseViewModelDelegate<T>(clazz) {
    override fun getViewModelProvider(): ViewModelProvider =
        ViewModelProviders.of(fragment, ViewModelWrapperFactory(factory))
}


class ViewModelFragmentFactoryProviderDelegate<T : ViewModel>(
    private val fragment: Fragment,
    private val factoryProvider: () -> ViewModelProvider.Factory,
    clazz: Class<T>
) : BaseViewModelDelegate<T>(clazz) {
    override fun getViewModelProvider(): ViewModelProvider =
        ViewModelProviders.of(fragment, factoryProvider())
}


abstract class BaseViewModelDelegate<T : ViewModel>(private val clazz: Class<T>) {
    @Volatile
    private var _value: T? = null

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        val vm1 = _value
        if (vm1 != null) {
            return vm1
        }

        synchronized(this) {
            var vm2 = _value
            if (vm2 == null) {
                vm2 = getViewModelProvider().get(clazz)
                    .also { _value = it }
            }
            return vm2
        }
    }

    protected abstract fun getViewModelProvider(): ViewModelProvider

}
