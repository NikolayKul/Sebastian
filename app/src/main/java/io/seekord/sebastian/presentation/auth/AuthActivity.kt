package io.seekord.sebastian.presentation.auth

import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ActivityAuthBinding
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.presentation.base.BaseActivity
import io.seekord.sebastian.utils.view.textString
import javax.inject.Inject

/**
 * Created by Nikolay Kulachenko
 */
class AuthActivity : BaseActivity(), AuthMvpView, AuthHandler {

    @Inject
    @InjectPresenter
    lateinit var presenter: AuthPresenter
    private lateinit var binding: ActivityAuthBinding
    private val accountManager by lazy { AccountManager.get(this) }
    private var accountResponse: AccountAuthenticatorResponse? = null
    private var accountResult: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        binding.handler = this
        initAccountResponse()
    }

    override fun finish() {
        applyAccountResult()
        super.finish()
    }

    @ProvidePresenter
    override fun providePresenter() = presenter.also { it.init(intent) }

    override fun injectSelf(component: ActivityComponent) {
        component.inject(this)
    }

    override fun showLoading() {
        binding.isLoading = true
    }

    override fun hideLoading() {
        binding.isLoading = false
    }

    override fun createAccount(params: CreateAccountParams) {
        val account = Account(params.name, params.accountType)
        accountManager.addAccountExplicitly(account, params.password, null)
        accountManager.setAuthToken(account, params.tokenType, params.token)
    }

    override fun updateAccount(params: UpdateAccountParams) {
        val account = Account(params.name, params.accountType)
        accountManager.setPassword(account, params.password)
    }

    override fun setAuthResult(intent: Intent) {
        accountResult = intent.extras
        setResult(Activity.RESULT_OK, intent)
    }

    override fun onLoginClick() {
        with(binding) {
            presenter.auth(etLogin.textString(), etPassword.textString())
        }
    }

    private fun applyAccountResult() {
        accountResponse?.apply {
            if (accountResult != null) {
                onResult(accountResult)
            } else {
                onError(AccountManager.ERROR_CODE_CANCELED, "canceled")
            }
        }
    }

    private fun initAccountResponse() {
        accountResponse = intent.getParcelableExtra(AuthBundleOptions.ACCOUNT_RESPONSE)
        accountResponse?.onRequestContinued()
    }

}