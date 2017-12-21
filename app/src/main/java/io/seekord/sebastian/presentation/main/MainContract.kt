package io.seekord.sebastian.presentation.main

import io.seekord.sebastian.presentation.base.ErrorMvpView

/**
 * Created by nikolay
 */

interface MainMvpView : ErrorMvpView {
    fun showCurrentAuthToken(token: String)
}