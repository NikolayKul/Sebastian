package io.seekord.sebastian.di

import android.app.Application

/**
 * Created by nikolay
 */

object DependencyManager {
    public lateinit var applicationComponent: ApplicationComponent

    fun init(app: Application) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(app))
                .build()
    }

}