package io.seekord.sebastian.presentation.auth

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ActivityAuthBinding
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.presentation.base.BaseActivity
import io.seekord.sebastian.presentation.main.MainActivity
import io.seekord.sebastian.utils.view.textString
import javax.inject.Inject

/**
 * Created by Nikolay Kulachenko
 */
class AuthActivity : BaseActivity(), AuthMvpView, AuthHandler {

    @Inject @InjectPresenter lateinit var presenter: AuthPresenter
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        binding.handler = this
    }

    @ProvidePresenter
    override fun providePresenter() = presenter

    override fun injectSelf(component: ActivityComponent) {
        component.inject(this)
    }

    override fun showLoading() {
        binding.isLoading = true
    }

    override fun hideLoading() {
        binding.isLoading = false
    }

    override fun showLoginSuccess() {
        Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show()
        // TODO: temp
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onLoginClick() {
        with(binding) {
            presenter.auth(etLogin.textString(), etPassword.textString())
        }
    }

}