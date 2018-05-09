package com.nikolaykul.sebastian

import android.app.Activity
import android.app.Application
import com.nikolaykul.sebastian.di.AppInjector
import com.nikolaykul.sebastian.utils.common.TimberFactory
import com.nikolaykul.sebastian.utils.debug.StethoUtils
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject

/**
 * @author NikolayKul
 */

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        // This process is dedicated to LeakCanary for heap analysis
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }

        AppInjector.init(this)
        TimberFactory.init()
        JodaTimeAndroid.init(this)
        StethoUtils.init(this)
        LeakCanary.install(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

}