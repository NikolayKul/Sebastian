package io.seekord.sebastian.di

import android.app.Activity
import dagger.Module
import dagger.Subcomponent
import io.seekord.sebastian.presentation.auth.AuthActivity
import javax.inject.Scope

/**
 * Created by nikolay
 */

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: AuthActivity)
}


@Module
class ActivityModule(private val activity: Activity)


@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class PerActivity