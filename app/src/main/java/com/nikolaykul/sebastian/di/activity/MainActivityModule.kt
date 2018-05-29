package com.nikolaykul.sebastian.di.activity

import android.support.v7.app.AppCompatActivity
import com.nikolaykul.sebastian.di.fragment.FeedListFragmentModule
import com.nikolaykul.sebastian.di.fragment.PerFragment
import com.nikolaykul.sebastian.presentation.feed.list.FeedListFragment
import com.nikolaykul.sebastian.presentation.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [CommonActivityModule::class])
interface MainActivityModule {

    @Binds
    fun appCompat(activity: MainActivity): AppCompatActivity

    @PerFragment
    @ContributesAndroidInjector(modules = [FeedListFragmentModule::class])
    fun feedListFragment(): FeedListFragment

}