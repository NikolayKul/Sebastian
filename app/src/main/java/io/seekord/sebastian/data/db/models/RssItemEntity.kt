package io.seekord.sebastian.data.db.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class RssItemEntity(
        @PrimaryKey val id: String,
        val title: String,
        val description: String,
        val date: Long  // TODO: replace with a DateTime and add a converter
)
