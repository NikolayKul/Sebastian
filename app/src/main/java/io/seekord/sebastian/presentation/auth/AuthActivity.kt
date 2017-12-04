package io.seekord.sebastian.presentation.auth

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ActivityAuthBinding
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.presentation.base.BaseActivity
import javax.inject.Inject

/**
 * Created by Nikolay Kulachenko
 */
class AuthActivity : BaseActivity(), AuthMvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: AuthPresenter
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
    }

    @ProvidePresenter
    override fun providePresenter() = presenter

    override fun injectSelf(component: ActivityComponent) {
        component.inject(this)
    }

    override fun showLoading() = TODO("not implemented")

    override fun hideLoading() = TODO("not implemented")

}