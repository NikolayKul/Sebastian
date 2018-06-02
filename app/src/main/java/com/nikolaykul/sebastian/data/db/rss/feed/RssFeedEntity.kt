package com.nikolaykul.sebastian.data.db.rss.feed

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.nikolaykul.sebastian.data.db.rss.channel.RssChannelEntity
import org.joda.time.DateTime

@Entity(
    tableName = "rss_feeds",
    foreignKeys = [
        ForeignKey(
            entity = RssChannelEntity::class,
            parentColumns = ["id"],
            childColumns = ["channel_id"]
        )
    ]
)
data class RssFeedEntity(
    @PrimaryKey val id: String,

    @ColumnInfo(name = "channel_id", index = true)
    val channelId: String,

    val title: String,
    val description: String,
    val pubDate: DateTime?
)
