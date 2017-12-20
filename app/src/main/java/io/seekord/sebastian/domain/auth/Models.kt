package io.seekord.sebastian.domain.auth

/**
 * Created by nikolay
 */

data class AuthParams(val email: String, val password: String)

data class AccountAuthParams(
        val accountName: String,
        val password: String,
        val authTokenType: String)

data class AuthData(val accessToken: String)