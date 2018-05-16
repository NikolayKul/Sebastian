package com.nikolaykul.sebastian.di.application

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nikolaykul.sebastian.utils.vm.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Reusable
import kotlin.reflect.KClass

/**
 * Module that provides a [ViewModelProvider.Factory] and common [ViewModel]s
 *
 * These ViewModels must have dependencies that are **known at the compile time**.
 * Typically they are shared across other ViewModels
 */
@Module
interface ViewModelModule {

    /*
        Should declare this provider with a `Reusable` scope or without any at all.
        Don't provide it as a `Singleton` because it'd contain only shared ViewModels then
     */
    @Binds
    @Reusable
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}


/**
 * Special [MapKey] for [ViewModel]s
 */
@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)
