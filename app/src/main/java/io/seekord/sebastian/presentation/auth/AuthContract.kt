package io.seekord.sebastian.presentation.auth

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import io.seekord.sebastian.presentation.base.LoadingMvpView

/**
 * Created by nikolay
 */

@StateStrategyType(OneExecutionStateStrategy::class)
interface AuthMvpView : LoadingMvpView {
    fun showLoginSuccess()
}

interface AuthHandler {
    fun onLoginClick()
}