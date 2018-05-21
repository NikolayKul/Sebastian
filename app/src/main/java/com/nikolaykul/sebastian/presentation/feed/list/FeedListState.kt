package com.nikolaykul.sebastian.presentation.feed.list

import com.nikolaykul.sebastian.domain.rss.models.RssFeed

data class FeedListState(
        val isLoading: Boolean = false,
        val error: Exception? = null,
        val feeds: List<RssFeed>? = null
)