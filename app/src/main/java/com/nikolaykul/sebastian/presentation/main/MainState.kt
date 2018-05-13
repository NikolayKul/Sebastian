package com.nikolaykul.sebastian.presentation.main

import com.nikolaykul.sebastian.domain.rss.models.RssFeed

data class MainState(
        val isLoading: Boolean = false,
        val error: Exception? = null,
        val feeds: List<RssFeed>? = null
)