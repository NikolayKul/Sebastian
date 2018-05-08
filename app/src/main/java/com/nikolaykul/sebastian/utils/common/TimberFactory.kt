package com.nikolaykul.sebastian.utils.common

import com.nikolaykul.sebastian.BuildConfig
import timber.log.Timber

/**
 * @author NikolayKul
 */

object TimberFactory {
    fun init() {
        // we can add Crashlytics/Analytics/etc Trees
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
