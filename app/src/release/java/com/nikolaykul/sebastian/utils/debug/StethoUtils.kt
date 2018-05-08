@file:Suppress("PackageDirectoryMismatch", "KDocUnresolvedReference", "UNUSED_PARAMETER")

package com.nikolaykul.sebastian.utils.debug

import android.app.Application
import okhttp3.Interceptor

/**
 * No-op [Stetho] implementation for release builds
 */
object StethoUtils {

    fun init(app: Application) {
        // no-op
    }

    fun provideInterceptor(): Interceptor = Interceptor { it.proceed(it.request()) }

}
