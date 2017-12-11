package io.seekord.sebastian.presentation.auth

import com.arellomobile.mvp.InjectViewState
import io.seekord.sebastian.domain.auth.AuthUseCase
import io.seekord.sebastian.domain.auth.models.AuthCredentials
import io.seekord.sebastian.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Created by nikolay
 */

@InjectViewState
class AuthPresenter @Inject constructor(private val authUseCase: AuthUseCase)
    : BasePresenter<AuthMvpView>() {

    fun auth(authCredentials: AuthCredentials) {
        try {
            viewState.showLoading()
            authUseCase.auth(authCredentials)
        } catch (error: Exception) {
            viewState.showError(error)
        } finally {
            viewState.hideLoading()
        }
    }

}