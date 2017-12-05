package io.seekord.sebastian.presentation.auth

import com.arellomobile.mvp.InjectViewState
import io.seekord.sebastian.presentation.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by nikolay
 */

@InjectViewState
class AuthPresenter @Inject constructor() : BasePresenter<AuthMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Timber.d("first attach view")
    }

    override fun attachView(view: AuthMvpView?) {
        super.attachView(view)
        Timber.d("attach view")
    }

    override fun detachView(view: AuthMvpView?) {
        super.detachView(view)
        Timber.d("detach view")
    }

    override fun destroyView(view: AuthMvpView?) {
        super.destroyView(view)
        Timber.d("destroy view")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("destroy presenterFromDagger")
    }

}