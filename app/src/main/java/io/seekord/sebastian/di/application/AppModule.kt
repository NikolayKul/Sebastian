package io.seekord.sebastian.di.application

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.seekord.sebastian.di.activity.ActivityModule
import io.seekord.sebastian.di.activity.PerActivity
import io.seekord.sebastian.presentation.main.MainActivity

/**
 * @author NikolayKul
 */

@Module(includes = [AndroidSupportInjectionModule::class])
interface AppModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    fun mainActivity(): MainActivity

}
