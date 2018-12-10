package com.nikolaykul.sebastian.di.activity

import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsViewModel
import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsViewModel_AssistedFactory
import dagger.Binds
import dagger.Module

@Module
interface FeedDetailsActivityModule {

    @Binds
    fun viewModelFactory(factory: FeedDetailsViewModel_AssistedFactory): FeedDetailsViewModel.Factory

}
