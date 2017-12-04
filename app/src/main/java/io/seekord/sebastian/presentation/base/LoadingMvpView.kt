package io.seekord.sebastian.presentation.base

import com.arellomobile.mvp.MvpView

/**
 * Created by nikolay
 */

interface LoadingMvpView : MvpView {
    fun showLoading()
    fun hideLoading()
}