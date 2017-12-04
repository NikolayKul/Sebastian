package io.seekord.sebastian.presentation.auth

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.seekord.sebastian.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Created by nikolay
 */

@InjectViewState
class AuthPresenter @Inject constructor() : BasePresenter<AuthMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.d("presenter", "first attach view")
    }

    override fun attachView(view: AuthMvpView?) {
        super.attachView(view)
        Log.d("presenter", "attach view")
    }

    override fun detachView(view: AuthMvpView?) {
        super.detachView(view)
        Log.d("presenter", "detach view")
    }

    override fun destroyView(view: AuthMvpView?) {
        super.destroyView(view)
        Log.d("presenter", "destroy view")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("presenter", "destroy presenterFromDagger")
    }

}