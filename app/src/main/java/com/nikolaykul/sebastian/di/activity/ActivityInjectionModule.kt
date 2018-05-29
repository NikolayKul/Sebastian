package com.nikolaykul.sebastian.di.activity

import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsActivity
import com.nikolaykul.sebastian.presentation.feed.list.FeedListActivity
import com.nikolaykul.sebastian.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityInjectionModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun mainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [FeedListActivityModule::class])
    fun feedListActivity(): FeedListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [FeedDetailsActivityModule::class])
    fun feedDetailsActivity(): FeedDetailsActivity

}
