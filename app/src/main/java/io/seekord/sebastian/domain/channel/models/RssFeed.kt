package io.seekord.sebastian.domain.channel.models

import org.joda.time.DateTime

data class RssFeed(
        val id: String,
        val title: String,
        val description: String,
        val date: DateTime?
)
