package io.seekord.sebastian.presentation.splash

import android.content.Intent
import com.arellomobile.mvp.presenter.InjectPresenter
import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ActivitySplashBinding
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.presentation.base.BaseActivity
import io.seekord.sebastian.presentation.main.MainActivity
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
        val intent = MainActivity.createStartIntent(this).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
    }

}
