package com.nikolaykul.sebastian.domain.rss.models

data class RssChannel(
    val id: String,
    val title: String,
    val link: String,
    val description: String,
    val feeds: List<RssFeed>
)
