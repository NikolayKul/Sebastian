package com.nikolaykul.sebastian.di.fragment

import android.arch.lifecycle.ViewModel
import com.nikolaykul.sebastian.di.application.ViewModelKey
import com.nikolaykul.sebastian.presentation.feed.list.FeedListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FeedListFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(FeedListViewModel::class)
    fun feedListViewModel(viewModel: FeedListViewModel): ViewModel

}
