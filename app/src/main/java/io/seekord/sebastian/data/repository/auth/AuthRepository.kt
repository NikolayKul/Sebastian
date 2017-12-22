package io.seekord.sebastian.data.repository.auth

import dagger.Reusable
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

    suspend fun auth(authParams: AuthParams) = sourceRemote.auth(authParams)

    suspend fun getAuthData() = sourceLocal.get() ?: throw TokenNotFoundException()

    suspend fun saveAuthData(authData: AuthData) {
        sourceLocal.save(authData)
    }

}