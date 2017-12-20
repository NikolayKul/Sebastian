package io.seekord.sebastian.data.service.auth

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.seekord.sebastian.di.DependencyManager
import javax.inject.Inject

/**
 * Created by nikolay
 */

class SebastianAuthenticatorService : Service() {
    @Inject lateinit var authenticator: SebastianAuthenticator

    override fun onCreate() {
        super.onCreate()
        DependencyManager.getApplicationComponent().inject(this)
    }

    override fun onBind(intent: Intent): IBinder = authenticator.iBinder

}