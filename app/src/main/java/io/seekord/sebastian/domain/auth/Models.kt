package io.seekord.sebastian.domain.auth

/**
 * Created by nikolay
 */

data class AuthParams(
        val username: String,
        val password: String,
        val authTokenType: String)

data class AuthData(val accessToken: String)