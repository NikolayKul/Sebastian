package io.seekord.sebastian.di

import android.app.Activity
import android.app.Application
import io.seekord.sebastian.di.application.ApplicationComponent
import io.seekord.sebastian.di.application.ApplicationModule
import io.seekord.sebastian.di.application.DaggerApplicationComponent

/**
 * @author NikolayKul
 */

object DependencyManager {
    lateinit var appComponent: ApplicationComponent
        private set

    fun init(app: Application) {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(app))
                .build()
    }

    fun createActivityComponent(activity: Activity): ActivityComponent = appComponent
            .plusActivityComponent(ActivityModule(activity))

}