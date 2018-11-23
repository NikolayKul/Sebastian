package com.nikolaykul.sebastian.presentation.feed.details

import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.base.ViewState

data class FeedDetailsState(
    val feed: RssFeed? = null
) : ViewState