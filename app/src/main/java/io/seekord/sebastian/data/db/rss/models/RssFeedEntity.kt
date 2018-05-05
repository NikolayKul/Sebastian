package io.seekord.sebastian.data.db.rss.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import org.joda.time.DateTime

@Entity(
        tableName = "rss_feeds",
        foreignKeys = [
            ForeignKey(
                    entity = RssChannelEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["channel_id"])
        ]
)
data class RssFeedEntity(
        @PrimaryKey val id: String,

        @ColumnInfo(name = "channel_id")
        val channelId: String,

        val title: String,
        val description: String,
        val date: DateTime?
)
