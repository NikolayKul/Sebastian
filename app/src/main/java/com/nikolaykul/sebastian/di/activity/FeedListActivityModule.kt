package com.nikolaykul.sebastian.di.activity

import android.arch.lifecycle.ViewModel
import com.nikolaykul.sebastian.di.application.ViewModelKey
import com.nikolaykul.sebastian.presentation.BaseNavigator
import com.nikolaykul.sebastian.presentation.feed.list.FeedListActivity
import com.nikolaykul.sebastian.presentation.feed.list.FeedListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Navigator

@Module
class FeedListActivityModule {

    @Provides
    fun navigator(activity: FeedListActivity): Navigator = BaseNavigator(activity)

    @Provides
    @IntoMap
    @ViewModelKey(FeedListViewModel::class)
    fun feedListViewModel(viewModel: FeedListViewModel): ViewModel = viewModel

}
