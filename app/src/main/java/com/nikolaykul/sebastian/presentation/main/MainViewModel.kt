package com.nikolaykul.sebastian.presentation.main

import com.nikolaykul.sebastian.presentation.base.StatefulViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : StatefulViewModel<MainState>() {
    override val defaultState = MainState()
}
