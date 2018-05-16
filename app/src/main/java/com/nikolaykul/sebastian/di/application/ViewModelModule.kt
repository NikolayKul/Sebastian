package com.nikolaykul.sebastian.di.application

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsViewModel
import com.nikolaykul.sebastian.presentation.feed.list.FeedListViewModel
import com.nikolaykul.sebastian.utils.vm.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * Special [MapKey] for [ViewModel]s
 */
@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)


@Module
interface ViewModelModule {

    @Binds
    @Singleton
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FeedListViewModel::class)
    fun feedListViewModel(viewModel: FeedListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedDetailsViewModel::class)
    fun feedDetailsViewModel(viewModel: FeedDetailsViewModel): ViewModel

}
