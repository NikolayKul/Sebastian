package io.seekord.sebastian.data.service.auth

import android.accounts.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.Reusable
import io.seekord.sebastian.di.AppContext
import io.seekord.sebastian.domain.auth.AuthData
import io.seekord.sebastian.domain.auth.AuthParams
import io.seekord.sebastian.domain.auth.AuthUseCase
import io.seekord.sebastian.presentation.auth.AuthActivity
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by nikolay
 */

@Reusable
class SebastianAuthenticator @Inject constructor(
        @AppContext private val context: Context,
        private val authUseCase: AuthUseCase
) : AbstractAccountAuthenticator(context) {

    override fun editProperties(accountAuthenticatorResponse: AccountAuthenticatorResponse,
                                s: String): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun addAccount(response: AccountAuthenticatorResponse,
                            accountType: String,
                            authTokenType: String,
                            requiredFeatures: Array<String>,
                            options: Bundle): Bundle {
        return createLoginIntent(accountType, authTokenType, response, true)
    }

    @Throws(NetworkErrorException::class)
    override fun getAuthToken(response: AccountAuthenticatorResponse,
                              account: Account,
                              authTokenType: String,
                              options: Bundle): Bundle {
        val accountManager = AccountManager.get(context)
        var authToken: String? = accountManager.peekAuthToken(account, authTokenType)

        if (authToken.isNullOrEmpty()) {
            authToken = accountManager.getPassword(account)
                    ?.let { AuthParams(account.name, it, authTokenType) }
                    ?.let { fetchAuthTokenFromRemote(it) }
        }

        return if (authToken.isNullOrEmpty()) {
            createLoginIntent(account.type, authTokenType, response)
        } else {
            Bundle().apply {
                putString(AccountManager.KEY_ACCOUNT_NAME, account.name)
                putString(AccountManager.KEY_ACCOUNT_TYPE, account.type)
                putString(AccountManager.KEY_AUTHTOKEN, authToken)
            }
        }
    }

    @Throws(NetworkErrorException::class)
    override fun confirmCredentials(accountAuthenticatorResponse: AccountAuthenticatorResponse,
                                    account: Account,
                                    bundle: Bundle): Bundle? {
        return null
    }

    override fun getAuthTokenLabel(s: String): String? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun updateCredentials(accountAuthenticatorResponse: AccountAuthenticatorResponse,
                                   account: Account,
                                   s: String,
                                   bundle: Bundle): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun hasFeatures(accountAuthenticatorResponse: AccountAuthenticatorResponse,
                             account: Account,
                             strings: Array<String>): Bundle? {
        return null
    }

    private fun createLoginIntent(
            accountType: String,
            authTokenType: String,
            response: AccountAuthenticatorResponse,
            isNew: Boolean = false
    ): Bundle {
        val intent = Intent(context, AuthActivity::class.java).apply {
            putExtra(AuthActivity.BundleOptions.ACCOUNT_TYPE, accountType)
            putExtra(AuthActivity.BundleOptions.AUTH_TYPE, authTokenType)
            putExtra(AuthActivity.BundleOptions.IS_ADDING_NEW_ACCOUNT, isNew)
            putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        }
        return Bundle().apply {
            putParcelable(AccountManager.KEY_INTENT, intent)
        }
    }

    private fun fetchAuthTokenFromRemote(accountParams: AuthParams): String? {
        return authUseCase.execute(accountParams)
                .doOnError { Timber.e(it) }
                .onErrorReturn { AuthData("") }
                .blockingGet()
                .accessToken
                .takeIf { it.isNotEmpty() }
    }

}