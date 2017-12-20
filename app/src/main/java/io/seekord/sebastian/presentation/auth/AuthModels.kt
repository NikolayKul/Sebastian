package io.seekord.sebastian.presentation.auth

import android.accounts.AccountManager
import io.seekord.sebastian.domain.auth.AuthData
import io.seekord.sebastian.domain.auth.AuthParams

/**
 * Created by nikolay
 */

object AuthBundleOptions {
    val ACCOUNT_RESPONSE = AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE
    val ACCOUNT_NAME = AccountManager.KEY_ACCOUNT_NAME
    val ACCOUNT_TYPE = AccountManager.KEY_ACCOUNT_TYPE
    val AUTH_TOKEN = AccountManager.KEY_AUTHTOKEN
    val AUTH_TYPE = "AUTH_TYPE"
    val USER_PASS = "USER_PASS"
    val IS_ADDING_NEW_ACCOUNT = "IS_ADDING_NEW_ACCOUNT"
}

class CreateAccountParams(
        authParams: AuthParams,
        authData: AuthData,
        accountType: String
) : CommonAccountParams(authParams, accountType) {
    val token = authData.accessToken
    val tokenType = authParams.authTokenType
}

class UpdateAccountParams(
        authParams: AuthParams,
        accountType: String
) : CommonAccountParams(authParams, accountType)

open class CommonAccountParams(
        authParams: AuthParams,
        val accountType: String
) {
    val name = authParams.username
    val password = authParams.password
}