package io.seekord.sebastian.utils.logger

import io.seekord.sebastian.BuildConfig
import timber.log.Timber

/**
 * @author NikolayKul
 */

object TimberFactory {
    fun init() {
        // TODO: add Crashlytics Tree
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
