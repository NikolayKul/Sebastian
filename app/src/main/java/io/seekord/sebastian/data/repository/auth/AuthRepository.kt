package io.seekord.sebastian.data.repository.auth

import dagger.Reusable
import io.reactivex.Completable
import io.reactivex.Single
import io.seekord.sebastian.domain.auth.models.AuthCredentials
import io.seekord.sebastian.domain.auth.models.AuthData
import javax.inject.Inject

/**
 * Created by nikolay
 */

@Reusable
class AuthRepository @Inject constructor() {

    fun auth(authCredentials: AuthCredentials): Single<AuthData> = TODO("Not implemented yet")

    fun getAuthData(): Single<AuthData> = TODO("Not implemented yet")

    fun saveAuthData(data: AuthData): Completable = TODO("Not implemented yet")

}