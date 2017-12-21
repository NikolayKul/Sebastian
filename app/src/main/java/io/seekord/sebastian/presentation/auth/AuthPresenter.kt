package io.seekord.sebastian.presentation.auth

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
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

    fun auth(username: String, password: String) {
        val authParams = AuthParams(username, password)
        authUseCase.execute(authParams)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showLoading() }
                .doOnTerminate { viewState.hideLoading() }
                .subscribe(
                        { viewState.showLoginSuccess() },
                        { viewState.showError(it) })
                .attachToLifecycle()
    }

}