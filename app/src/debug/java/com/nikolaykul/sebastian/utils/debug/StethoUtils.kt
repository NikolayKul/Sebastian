@file:Suppress("PackageDirectoryMismatch", "KDocUnresolvedReference")

package com.nikolaykul.sebastian.utils.debug

import android.app.Application
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor

/**
 * Real [Stetho] implementation for debug builds
 */
object StethoUtils {

    fun init(app: Application) {
        Stetho.initializeWithDefaults(app)
    }

    fun provideInterceptor(): Interceptor = StethoInterceptor()

}
