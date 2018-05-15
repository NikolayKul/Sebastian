package com.nikolaykul.sebastian.di.activity

import com.nikolaykul.sebastian.presentation.BaseNavigator
import com.nikolaykul.sebastian.presentation.feed.list.FeedListActivity
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Navigator

@Module
class FeedListActivityModule {

    @Provides
    fun navigator(activity: FeedListActivity): Navigator = BaseNavigator(activity)

}
