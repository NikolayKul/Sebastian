package io.seekord.sebastian.data.source.auth

import com.orhanobut.hawk.Hawk
import dagger.Reusable
import io.seekord.sebastian.domain.auth.AuthData
import javax.inject.Inject

/**
 * Created by nikolay
 */

private const val KEY_AUTH = "key_auth"

@Reusable
class AuthSourceLocal @Inject constructor() {

    fun save(authData: AuthData) {
        Hawk.put(KEY_AUTH, authData)
    }

    fun get(): AuthData? = Hawk.get(KEY_AUTH)

}