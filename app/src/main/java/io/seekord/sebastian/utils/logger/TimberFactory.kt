package io.seekord.sebastian.utils.logger

import io.seekord.sebastian.BuildConfig
import timber.log.Timber

/**
 * Created by nikolay
 */

object TimberFactory {
    fun init() {
        // TODO: add different strategies (e.g. Crashlytics, LOG_ENABLED, DEBUG)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}