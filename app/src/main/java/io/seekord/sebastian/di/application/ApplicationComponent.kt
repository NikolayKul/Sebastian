package io.seekord.sebastian.di.application

import dagger.Component
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.di.ActivityModule
import javax.inject.Singleton

/**
 * @author NikolayKul
 */

@Singleton
@Component(modules = [ApplicationModule::class, RouterModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun plusActivityComponent(module: ActivityModule): ActivityComponent
}
