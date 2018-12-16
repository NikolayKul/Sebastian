package com.nikolaykul.sebastian.di.activity;

import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsViewModel;
import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsViewModel_AssistedFactory;

import dagger.Binds;
import dagger.Module;

// TODO: replace with a Kotlin file when corresponding bug will be fixed (https://github.com/google/dagger/issues/1359)

@Module
public interface FeedDetailsActivityModule {
    @Binds
    FeedDetailsViewModel.Factory viewModelFactory(FeedDetailsViewModel_AssistedFactory factory);
}
