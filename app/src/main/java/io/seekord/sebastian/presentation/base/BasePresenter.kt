package io.seekord.sebastian.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

/**
 * Created by nikolay
 */

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>()