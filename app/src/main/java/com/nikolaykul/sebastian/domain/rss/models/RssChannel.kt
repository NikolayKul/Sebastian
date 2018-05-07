package com.nikolaykul.sebastian.domain.rss.models

data class RssChannel(
        val title: String,
        val link: String,
        val description: String,
        val feeds: List<RssFeed>
)
