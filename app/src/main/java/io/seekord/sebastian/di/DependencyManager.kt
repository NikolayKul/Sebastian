package io.seekord.sebastian.di

import android.app.Activity
import android.app.Application

/**
 * Created by nikolay
 */

object DependencyManager {
    private lateinit var applicationComponent: ApplicationComponent

    fun init(app: Application) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(app))
                .build()
    }

    fun createActivityComponent(activity: Activity): ActivityComponent = applicationComponent
            .plusActivityComponent(ActivityModule(activity))

}