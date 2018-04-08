package io.seekord.sebastian

import android.app.Activity
import android.app.Application
import com.orhanobut.hawk.Hawk
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.seekord.sebastian.di.AppInjector
import io.seekord.sebastian.utils.logger.TimberFactory
import javax.inject.Inject

/**
 * @author NikolayKul
 */

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        TimberFactory.init()
        Hawk.init(this).build()
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

}