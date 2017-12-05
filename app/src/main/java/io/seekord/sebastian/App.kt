package io.seekord.sebastian

import android.app.Application
import io.seekord.sebastian.di.DependencyManager
import io.seekord.sebastian.utils.logger.TimberFactory

/**
 * Created by nikolay
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyManager.init(this)
        TimberFactory.init()
    }

}