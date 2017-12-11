package io.seekord.sebastian.data.repository.auth

import dagger.Reusable
import io.seekord.sebastian.domain.auth.models.AuthCredentials
import io.seekord.sebastian.domain.auth.models.AuthData
import javax.inject.Inject

/**
 * Created by nikolay
 */

@Reusable
class AuthRepository @Inject constructor() {

    fun auth(authCredentials: AuthCredentials): AuthData = TODO("Not implemented yet")

    fun getAuthData(): AuthData = TODO("Not implemented yet")

    fun saveAuthData(data: AuthData) {
        TODO("Not implemented yet")
    }

}