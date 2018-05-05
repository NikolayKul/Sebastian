package io.seekord.sebastian.utils.common

import io.seekord.sebastian.BuildConfig
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
