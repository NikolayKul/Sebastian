package io.seekord.sebastian.data.api

import io.reactivex.Single
import io.seekord.sebastian.domain.auth.AuthData
import io.seekord.sebastian.domain.auth.AuthParams

/**
 * Created by nikolay
 */
interface SebastianApi {

    fun auth(credentials: AuthParams): Single<AuthData>

}