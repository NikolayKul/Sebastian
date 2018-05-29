package com.nikolaykul.sebastian.utils.vm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import kotlin.reflect.KProperty


/**
 * An util function that creates an instance of a [ViewModelActivityDelegate]
 */
inline fun <reified T : ViewModel> viewModelActivityDelegate(
    activity: FragmentActivity,
    noinline factoryProvider: FactoryProvider? = null
) = ViewModelActivityDelegate(activity, factoryProvider, T::class.java)


/**
 * A custom Delegate that creates a generic [ViewModel] using a [ViewModelProvider.Factory]
 *
 * The provided [ViewModelProvider.Factory] is wrapped into a [FactoryProvider] in order to
 * get the instance of the factory as late as possible. It's useful when
 * [ViewModelProvider.Factory] is injected or initialized after the delegate
 */
class ViewModelActivityDelegate<T : ViewModel>(
    private val activity: FragmentActivity,
    private val factoryProvider: FactoryProvider?,
    clazz: Class<T>
) : BaseViewModelDelegate<T>(clazz) {
    override fun getViewModelProvider() = ViewModelProviders.of(activity, factoryProvider?.invoke())
}


/**
 * An util function that creates an instance of a [ViewModelFragmentDelegate]
 */
inline fun <reified T : ViewModel> viewModelFragmentDelegate(
    fragment: Fragment,
    noinline factoryProvider: FactoryProvider? = null
) = ViewModelFragmentDelegate(fragment, factoryProvider, T::class.java)


/**
 * A custom Delegate that creates a generic [ViewModel] using a [ViewModelProvider.Factory]
 *
 * The provided [ViewModelProvider.Factory] is wrapped into a [FactoryProvider] in order to
 * get the instance of the factory as late as possible. It's useful when
 * [ViewModelProvider.Factory] is injected or initialized after the delegate
 */
class ViewModelFragmentDelegate<T : ViewModel>(
    private val fragment: Fragment,
    private val factoryProvider: FactoryProvider?,
    clazz: Class<T>
) : BaseViewModelDelegate<T>(clazz) {
    override fun getViewModelProvider() = ViewModelProviders.of(fragment, factoryProvider?.invoke())
}


abstract class BaseViewModelDelegate<T : ViewModel>(private val clazz: Class<T>) {
    @Volatile
    private var _viewModel: T? = null

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        val vm1 = _viewModel
        if (vm1 != null) {
            return vm1
        }

        return synchronized(this) {
            var vm2 = _viewModel
            if (vm2 == null) {
                vm2 = getViewModelProvider().get(clazz)
                    .also { _viewModel = it }
            }
            return@synchronized vm2
        }
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: T) {
        _viewModel = value
    }

    protected abstract fun getViewModelProvider(): ViewModelProvider

}


/**
 * Alias for a high order function that returns a [ViewModelProvider.Factory]
 */
private typealias FactoryProvider = () -> ViewModelProvider.Factory
