package com.nikolaykul.sebastian.di.activity

import com.nikolaykul.sebastian.di.fragment.FeedListFragmentModule
import com.nikolaykul.sebastian.di.fragment.PerFragment
import com.nikolaykul.sebastian.presentation.feed.list.FeedListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [FeedListFragmentModule::class])
    fun feedListFragment(): FeedListFragment

}