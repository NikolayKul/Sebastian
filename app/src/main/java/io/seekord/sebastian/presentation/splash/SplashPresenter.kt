package io.seekord.sebastian.presentation.splash

import com.arellomobile.mvp.InjectViewState
import io.seekord.sebastian.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Created by nikolay
 */

@InjectViewState
class SplashPresenter @Inject constructor() : BasePresenter<SplashMvpView>() {

    override fun attachView(view: SplashMvpView?) {
        super.attachView(view)
        viewState?.navigateToMain()
    }

}