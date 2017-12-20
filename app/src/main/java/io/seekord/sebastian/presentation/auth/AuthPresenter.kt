package io.seekord.sebastian.presentation.auth

import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.seekord.sebastian.domain.auth.AuthData
import io.seekord.sebastian.domain.auth.AuthParams
import io.seekord.sebastian.domain.auth.AuthUseCase
import io.seekord.sebastian.presentation.base.BasePresenter
import javax.inject.Inject


/**
 * Created by nikolay
 */

@InjectViewState
class AuthPresenter @Inject constructor(
        private val authUseCase: AuthUseCase
) : BasePresenter<AuthMvpView>() {
    private lateinit var accountType: String
    private var isNewAccount: Boolean = false

    fun init(intent: Intent) {
        intent.apply {
            accountType = getStringExtra(AuthBundleOptions.ACCOUNT_TYPE)
            isNewAccount = getBooleanExtra(AuthBundleOptions.IS_ADDING_NEW_ACCOUNT, false)
        }
    }

    fun auth(authParams: AuthParams) {
        authUseCase.execute(authParams)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showLoading() }
                .doOnEvent { _, _ -> viewState.hideLoading() }
                .subscribe(
                        { onAuthSuccess(authParams, it) },
                        { viewState.showError(it) })
                .attachToLifecycle()
    }

    private fun onAuthSuccess(authParams: AuthParams, authData: AuthData) {
        if (isNewAccount) {
            createAccount(authParams, authData)
        } else {
            updateAccount(authParams)
        }
        setAuthResult(authParams, authData)
        viewState.finish()  // TODO: replace with a router call
    }

    private fun createAccount(authParams: AuthParams, authData: AuthData) {
        val params = CreateAccountParams(authParams, authData, accountType)
        viewState.createAccount(params)
    }

    private fun updateAccount(authParams: AuthParams) {
        val params = UpdateAccountParams(authParams, accountType)
        viewState.updateAccount(params)
    }

    private fun setAuthResult(authParams: AuthParams, authData: AuthData) {
        val intent = Intent().apply {
            putExtra(AuthBundleOptions.ACCOUNT_NAME, authParams.username)
            putExtra(AuthBundleOptions.ACCOUNT_TYPE, authParams.authTokenType)
            putExtra(AuthBundleOptions.USER_PASS, authParams.password)
            putExtra(AuthBundleOptions.AUTH_TOKEN, authData.accessToken)
        }
        viewState.setAuthResult(intent)
    }

}