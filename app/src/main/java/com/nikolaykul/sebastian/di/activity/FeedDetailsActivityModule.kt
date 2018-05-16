package com.nikolaykul.sebastian.di.activity

import android.arch.lifecycle.ViewModel
import com.nikolaykul.sebastian.di.application.ViewModelKey
import com.nikolaykul.sebastian.domain.rss.FeedId
import com.nikolaykul.sebastian.presentation.BaseNavigator
import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsActivity
import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsViewModel
import com.nikolaykul.sebastian.presentation.feed.details.feedId
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Navigator

@Module
class FeedDetailsActivityModule {

    @Provides
    fun navigator(activity: FeedDetailsActivity): Navigator = BaseNavigator(activity)

    @Provides
    fun feedId(activity: FeedDetailsActivity): FeedId = activity.feedId

    @Provides
    @IntoMap
    @ViewModelKey(FeedDetailsViewModel::class)
    fun feedDetailsViewModel(viewModel: FeedDetailsViewModel): ViewModel = viewModel

}