package com.nikolaykul.sebastian.di.application

import com.nikolaykul.sebastian.di.activity.MainActivityModule
import com.nikolaykul.sebastian.di.activity.PerActivity
import com.nikolaykul.sebastian.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * @author NikolayKul
 */

@Module(includes = [AndroidSupportInjectionModule::class])
interface AppInjectionModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun mainActivity(): MainActivity

}
