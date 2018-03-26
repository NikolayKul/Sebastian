package io.seekord.sebastian.presentation.splash

import com.arellomobile.mvp.presenter.InjectPresenter
import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ActivitySplashBinding
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.presentation.base.BaseActivity
import javax.inject.Inject

/**
 * Created by nikolay
 */

class SplashActivity : BaseActivity<ActivitySplashBinding>(), SplashMvpView {

    @Inject @InjectPresenter lateinit var presenter: SplashPresenter

    override fun getLayoutId() = R.layout.activity_splash

    override fun providePresenter() = presenter

    override fun injectSelf(component: ActivityComponent) {
        component.inject(this)
    }

    override fun navigateToMain() {
        TODO("not implemented")
    }

}
