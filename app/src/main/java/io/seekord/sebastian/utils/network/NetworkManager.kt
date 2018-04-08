package io.seekord.sebastian.utils.network

import android.content.Context
import android.net.ConnectivityManager
import io.seekord.sebastian.di.application.AppContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author NikolayKul
 */

@Singleton
class NetworkManager @Inject constructor(@AppContext context: Context) {
    private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isNetworkAvailable() = cm.activeNetworkInfo?.isConnected ?: false

}