package io.seekord.sebastian.di

import android.app.Activity
import dagger.Module
import dagger.Subcomponent
import io.seekord.sebastian.presentation.main.MainActivity
import io.seekord.sebastian.presentation.splash.SplashActivity
import javax.inject.Scope

/**
 * Created by nikolay
 */

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: SplashActivity)
    fun inject(activity: MainActivity)
}


@Module
class ActivityModule(private val activity: Activity)


@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class PerActivity