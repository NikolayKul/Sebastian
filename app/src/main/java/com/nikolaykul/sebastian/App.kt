package com.nikolaykul.sebastian

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.nikolaykul.sebastian.di.AppInjector
import com.nikolaykul.sebastian.utils.common.TimberFactory
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
        AppInjector.init(this)
        TimberFactory.init()
        JodaTimeAndroid.init(this)
        Stetho.initializeWithDefaults(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

}