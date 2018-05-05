package io.seekord.sebastian.data.db.rss.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class RssChannelEntity(
        @PrimaryKey val id: String,
        val title: String,
        val link: String,
        val description: String
)

// TODO: add feeds
