package io.seekord.sebastian.data.db.rss.channel

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "rss_channels")
data class RssChannelEntity(
        @PrimaryKey val id: String,
        val title: String,
        val link: String,
        val description: String
)
