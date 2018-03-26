package io.seekord.sebastian.utils

import android.content.Context
import android.net.ConnectivityManager
import io.seekord.sebastian.di.AppContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by nikolay
 */

@Singleton
class NetworkManager @Inject constructor(@AppContext context: Context) {
    private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isNetworkAvailable() = cm.activeNetworkInfo?.isConnected ?: false

}