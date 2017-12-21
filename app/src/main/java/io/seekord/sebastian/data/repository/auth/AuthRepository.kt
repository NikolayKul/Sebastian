package io.seekord.sebastian.data.repository.auth

import dagger.Reusable
import io.reactivex.Completable
import io.reactivex.Single
import io.seekord.sebastian.data.source.auth.AuthSourceLocal
import io.seekord.sebastian.data.source.auth.AuthSourceRemote
import io.seekord.sebastian.domain.auth.AuthData
import io.seekord.sebastian.domain.auth.AuthParams
import io.seekord.sebastian.domain.auth.TokenNotFoundException
import javax.inject.Inject

/**
 * Created by nikolay
 */

@Reusable
class AuthRepository @Inject constructor(
        private val sourceLocal: AuthSourceLocal,
        private val sourceRemote: AuthSourceRemote
) {

    fun auth(authParams: AuthParams) = sourceRemote.auth(authParams)

    fun getAuthData(): Single<AuthData> = Single.defer {
        val authData = sourceLocal.get()
        if (authData != null) {
            Single.just(authData)
        } else {
            Single.error(TokenNotFoundException())
        }
    }

    fun saveAuthData(authData: AuthData): Completable {
        return Completable.fromCallable { sourceLocal.save(authData) }
    }

}