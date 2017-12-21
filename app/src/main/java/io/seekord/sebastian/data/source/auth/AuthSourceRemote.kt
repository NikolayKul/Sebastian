package io.seekord.sebastian.data.source.auth

import dagger.Reusable
import io.seekord.sebastian.data.api.SebastianApi
import io.seekord.sebastian.domain.auth.AuthParams
import javax.inject.Inject

/**
 * Created by nikolay
 */

@Reusable
class AuthSourceRemote @Inject constructor(
        private val api: SebastianApi
) {

    fun auth(params: AuthParams) = api.auth(params)

}