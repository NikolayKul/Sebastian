package io.seekord.sebastian.utils.logger

import io.seekord.sebastian.BuildConfig
import timber.log.Timber

/**
 * Created by nikolay
 */

object TimberFactory {
    fun init() {
        // TODO: add Crashlytics Tree
        val tree = if (BuildConfig.DEBUG) Timber.DebugTree() else NoLogTree()
        Timber.plant(tree)
    }
}

private class NoLogTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // no-op
    }
}