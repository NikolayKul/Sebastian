package com.nikolaykul.sebastian.presentation.main

import com.nikolaykul.sebastian.presentation.SCREEN_FEED_LIST
import com.nikolaykul.sebastian.presentation.base.StatefulViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: Router
) : StatefulViewModel<MainState>() {

    fun onFeedListClicked() {
        router.navigateTo(SCREEN_FEED_LIST)
    }

}