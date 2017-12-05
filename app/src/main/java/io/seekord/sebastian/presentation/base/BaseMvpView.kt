package io.seekord.sebastian.presentation.base

import com.arellomobile.mvp.MvpView

/**
 * Created by nikolay
 */

interface ErrorMvpView : MvpView {
    fun showError(error: Throwable)
}

interface LoadingMvpView : ErrorMvpView {
    fun showLoading()
    fun hideLoading()
}