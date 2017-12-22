package io.seekord.sebastian.presentation.auth

import com.arellomobile.mvp.InjectViewState
import io.seekord.sebastian.domain.auth.AuthParams
import io.seekord.sebastian.domain.auth.AuthUseCase
import io.seekord.sebastian.presentation.base.BasePresenter
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

/**
 * Created by nikolay
 */

@InjectViewState
class AuthPresenter @Inject constructor(
        private val authUseCase: AuthUseCase
) : BasePresenter<AuthMvpView>() {
    private var job: Job? = null

    fun auth(username: String, password: String) {
        job = doAuth(AuthParams(username, password))
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    private fun doAuth(authParams: AuthParams) = launch(UI) {
        viewState.showLoading()
        try {
            async { authUseCase.execute(authParams) }.await()
            viewState.showLoginSuccess()
        } catch (error: Exception) {
            viewState.showError(error)
        }
        viewState.hideLoading()
    }

}