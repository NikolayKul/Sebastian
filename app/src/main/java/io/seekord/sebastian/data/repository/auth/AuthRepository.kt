package io.seekord.sebastian.data.repository.auth

import dagger.Reusable

import io.reactivex.Completable
import io.reactivex.Single
import io.seekord.sebastian.data.api.SebastianApi
import io.seekord.sebastian.domain.auth.AuthData
import io.seekord.sebastian.domain.auth.AuthParams
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by nikolay
 */

private const val DEFAULT_DELAY = 2_000L

@Reusable
class AuthRepository @Inject constructor(
        private val api: SebastianApi
) {
    private val authData = AuthData("requestToken", "refreshToken")

    fun auth(authParams: AuthParams) = api.auth(authParams)

    fun getAuthData() = Single.just(authData)
            .delay(DEFAULT_DELAY, TimeUnit.MILLISECONDS)

    fun saveAuthData(data: AuthData) = Completable.complete()
            .delay(DEFAULT_DELAY, TimeUnit.MILLISECONDS)

}