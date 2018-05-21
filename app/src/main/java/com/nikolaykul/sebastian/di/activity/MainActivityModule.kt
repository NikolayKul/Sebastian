package com.nikolaykul.sebastian.di.activity

import android.support.v7.app.AppCompatActivity
import com.nikolaykul.sebastian.presentation.main.MainActivity
import dagger.Binds
import dagger.Module

@Module(includes = [CommonActivityModule::class])
interface MainActivityModule {

    @Binds
    fun appCompat(activity: MainActivity): AppCompatActivity

}