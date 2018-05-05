package io.seekord.sebastian.presentation.main

import io.seekord.sebastian.domain.rss.models.RssFeed
import io.seekord.sebastian.presentation.base.BasePresenter
import io.seekord.sebastian.presentation.base.ErrorMvpView

/**
 * @author NikolayKul
 */

interface MainMvpView : ErrorMvpView {
    fun showFeeds(feeds: List<RssFeed>)
}


abstract class MainPresenter : BasePresenter<MainMvpView>() {
    abstract fun loadFeeds()
}