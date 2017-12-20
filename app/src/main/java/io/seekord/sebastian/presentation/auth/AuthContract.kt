package io.seekord.sebastian.presentation.auth

import android.content.Intent
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import io.seekord.sebastian.presentation.base.LoadingMvpView

/**
 * Created by nikolay
 */

@StateStrategyType(OneExecutionStateStrategy::class)
interface AuthMvpView : LoadingMvpView {
    fun createAccount(params: CreateAccountParams)
    fun updateAccount(params: UpdateAccountParams)
    fun setAuthResult(intent: Intent)
    fun finish()    // TODO: replace with a router later
}

interface AuthHandler {
    fun onLoginClick()
}