package io.seekord.sebastian.presentation.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by nikolay
 */

@StateStrategyType(OneExecutionStateStrategy::class)
interface ErrorMvpView : MvpView {
    fun showError(error: Throwable)
}

@StateStrategyType(OneExecutionStateStrategy::class)
interface LoadingMvpView : ErrorMvpView {
    fun showLoading()
    fun hideLoading()
}