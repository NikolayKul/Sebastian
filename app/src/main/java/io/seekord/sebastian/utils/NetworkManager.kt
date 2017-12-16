package io.seekord.sebastian.utils

import android.content.Context
import io.reactivex.Completable
import io.seekord.sebastian.di.AppContext
import io.seekord.sebastian.domain.base.NetworkException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by nikolay
 */
@Singleton
class NetworkManager @Inject constructor(@AppContext context: Context) {

    fun checkNetworkOrThrow(): Completable = Completable.defer {
        if (isNetworkAvailable()) {
            Completable.complete()
        } else {
            Completable.error(NetworkException())
        }
    }

    fun isNetworkAvailable() = true // TODO: stub

}
