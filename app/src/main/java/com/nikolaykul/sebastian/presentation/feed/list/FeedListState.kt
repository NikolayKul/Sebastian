package com.nikolaykul.sebastian.presentation.feed.list

import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.base.ViewState

data class FeedListState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val feeds: List<RssFeed>? = null
) : ViewState