package io.seekord.sebastian.presentation.main

import io.seekord.sebastian.domain.rss.models.RssItem
import io.seekord.sebastian.presentation.base.ErrorMvpView

/**
 * @author NikolayKul
 */

interface MainMvpView : ErrorMvpView {
    fun showRssPreviews(items: List<RssItem>)
}