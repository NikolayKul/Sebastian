package io.seekord.sebastian.presentation.main

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.seekord.sebastian.domain.auth.GetAuthTokenUseCase
import io.seekord.sebastian.presentation.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by nikolay
 */

@InjectViewState
class MainPresenter @Inject constructor(
        private val getAuthTokenUseCase: GetAuthTokenUseCase
) : BasePresenter<MainMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        showCurrentAuthToken()
    }

    private fun showCurrentAuthToken() {
        getAuthTokenUseCase.execute()
                .map { it.accessToken }
                .doOnError { Timber.e(it) }
                .onErrorReturn { "No auth token" }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { viewState.showCurrentAuthToken(it) },
                        { Timber.e(it) })
                .attachToLifecycle()
    }

}