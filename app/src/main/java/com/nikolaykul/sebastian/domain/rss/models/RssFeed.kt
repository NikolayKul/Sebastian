package com.nikolaykul.sebastian.domain.rss.models

import org.joda.time.DateTime

data class RssFeed(
        val id: String,
        val title: String,
        val description: String,
        val date: DateTime?
)
