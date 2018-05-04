package io.seekord.sebastian.data.db.rss.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.joda.time.DateTime

@Entity
data class RssItemEntity(
        @PrimaryKey val id: String,
        val title: String,
        val description: String,
        val date: DateTime
)
