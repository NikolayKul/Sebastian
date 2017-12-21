package io.seekord.sebastian

import android.app.Application
import com.orhanobut.hawk.Hawk
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
        Hawk.init(this).build()
    }

}