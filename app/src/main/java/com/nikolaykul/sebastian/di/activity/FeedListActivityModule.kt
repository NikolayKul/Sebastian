package com.nikolaykul.sebastian.di.activity

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.nikolaykul.sebastian.di.application.ViewModelKey
import com.nikolaykul.sebastian.presentation.feed.list.FeedListActivity
import com.nikolaykul.sebastian.presentation.feed.list.FeedListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [CommonActivityModule::class])
interface FeedListActivityModule {

    @Binds
    fun appCompat(activity: FeedListActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ViewModelKey(FeedListViewModel::class)
    fun feedListViewModel(viewModel: FeedListViewModel): ViewModel

}
