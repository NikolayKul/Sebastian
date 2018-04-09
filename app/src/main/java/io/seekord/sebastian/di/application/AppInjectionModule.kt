package io.seekord.sebastian.di.application

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.seekord.sebastian.di.activity.MainActivityModule
import io.seekord.sebastian.di.activity.PerActivity
import io.seekord.sebastian.presentation.main.MainActivity

/**
 * @author NikolayKul
 */

@Module(includes = [AndroidSupportInjectionModule::class])
interface AppInjectionModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun mainActivity(): MainActivity

}
