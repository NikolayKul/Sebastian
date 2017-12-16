package io.seekord.sebastian.utils

import android.content.Context
import io.seekord.sebastian.di.AppContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by nikolay
 */
@Singleton
class NetworkManager @Inject constructor(@AppContext context: Context) {

    fun isNetworkAvailable() = true // TODO: stub

}