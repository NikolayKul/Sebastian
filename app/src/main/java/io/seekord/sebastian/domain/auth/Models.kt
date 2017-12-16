package io.seekord.sebastian.domain.auth

/**
 * Created by nikolay
 */

data class AuthParams(val email: String, val password: String)

data class AuthData(val requestToken: String, val refreshToken: String)