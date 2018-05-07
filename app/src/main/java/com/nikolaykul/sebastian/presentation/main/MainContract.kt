package com.nikolaykul.sebastian.presentation.main

import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.base.BasePresenter
import com.nikolaykul.sebastian.presentation.base.ErrorMvpView

/**
 * @author NikolayKul
 */

interface MainMvpView : ErrorMvpView {
    fun showFeeds(feeds: List<RssFeed>)
}


abstract class MainPresenter : BasePresenter<MainMvpView>() {
    abstract fun loadFeeds()
}