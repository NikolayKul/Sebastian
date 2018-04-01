package io.seekord.sebastian.presentation.main

import android.content.Context
import android.content.Intent
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ActivityMainBinding
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.presentation.base.BaseActivity
import javax.inject.Inject

/**
 * @author NikolayKul
 */

class MainActivity : BaseActivity<ActivityMainBinding>(), MainMvpView {
    companion object {
        fun createStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject @InjectPresenter lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun getLayoutId() = R.layout.activity_main

    override fun injectSelf(component: ActivityComponent) {
        component.inject(this)
    }

    override fun showCurrentAuthToken(token: String) {
    }

}