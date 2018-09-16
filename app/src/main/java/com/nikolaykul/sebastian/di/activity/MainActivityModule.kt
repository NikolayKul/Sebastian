package com.nikolaykul.sebastian.di.activity

import android.arch.lifecycle.ViewModel
import com.nikolaykul.sebastian.di.application.ViewModelKey
import com.nikolaykul.sebastian.di.fragment.FeedListFragmentModule
import com.nikolaykul.sebastian.di.fragment.PerFragment
import com.nikolaykul.sebastian.presentation.feed.list.FeedListFragment
import com.nikolaykul.sebastian.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(viewModel: MainViewModel): ViewModel

    @PerFragment
    @ContributesAndroidInjector(modules = [FeedListFragmentModule::class])
    fun feedListFragment(): FeedListFragment

}