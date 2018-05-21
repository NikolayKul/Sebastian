package com.nikolaykul.sebastian.utils.vm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import kotlin.reflect.KProperty


/**
 * Alias for a high order function that returns a [ViewModelProvider.Factory]
 */
private typealias FactoryProvider = () -> ViewModelProvider.Factory


/**
 * An util function that creates an instance of a [ViewModelFactoryDelegate]
 */
inline fun <reified T : ViewModel> viewModelDelegate(
        activity: FragmentActivity,
        noinline factoryProvider: FactoryProvider? = null
) = ViewModelFactoryDelegate(T::class.java, activity, factoryProvider)


/**
 * A custom Delegate that creates a generic [ViewModel] using a [ViewModelProvider.Factory].
 * The provided [ViewModelProvider.Factory] is wrapped into a function in order to
 * get the instance of the factory as late as possible.
 *
 * It's useful when a [ViewModelProvider.Factory] is injected or just initialized after the delegate
 */
class ViewModelFactoryDelegate<T : ViewModel>(
        private val clazz: Class<T>,
        private val activity: FragmentActivity,
        private val factoryProvider: FactoryProvider?
) {

    private var _viewModel: T? = null

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        return _viewModel ?: getAndCache()
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: T) {
        _viewModel = value
    }

    private fun getAndCache() = ViewModelProviders.of(activity, factoryProvider?.invoke())
            .get(clazz)
            .also { _viewModel = it }

}
