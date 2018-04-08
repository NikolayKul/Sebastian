package io.seekord.sebastian.di

import dagger.Subcomponent
import io.seekord.sebastian.presentation.main.MainActivity
import javax.inject.Scope

/**
 * @author NikolayKul
 */

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}


@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class PerActivity