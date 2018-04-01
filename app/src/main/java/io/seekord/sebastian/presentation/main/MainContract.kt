package io.seekord.sebastian.presentation.main

import io.seekord.sebastian.presentation.base.ErrorMvpView

/**
 * @author NikolayKul
 */

interface MainMvpView : ErrorMvpView {
    fun showCurrentAuthToken(token: String)
}