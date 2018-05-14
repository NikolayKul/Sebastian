package com.nikolaykul.sebastian.di.activity

import com.nikolaykul.sebastian.presentation.BaseNavigator
import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsActivity
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Navigator

@Module
class FeedDetailsActivityModule {

    @Provides
    fun navigator(activity: FeedDetailsActivity): Navigator = BaseNavigator(activity)

}