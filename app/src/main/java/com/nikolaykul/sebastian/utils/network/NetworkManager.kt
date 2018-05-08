package com.nikolaykul.sebastian.utils.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author NikolayKul
 */

@Singleton
class NetworkManager @Inject constructor(app: Application) {
    private val cm = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isNetworkAvailable() = cm.activeNetworkInfo?.isConnected ?: false

}