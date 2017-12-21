package io.seekord.sebastian.domain.auth

/**
 * Created by nikolay
 */

data class AuthParams(val username: String, val password: String)

data class AuthData(val accessToken: String)