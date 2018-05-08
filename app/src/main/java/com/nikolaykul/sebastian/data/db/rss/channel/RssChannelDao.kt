package com.nikolaykul.sebastian.data.db.rss.channel

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface RssChannelDao {

    @Insert(onConflict = REPLACE)
    fun insert(channel: RssChannelEntity)

    @Insert(onConflict = REPLACE)
    fun insert(channels: List<RssChannelEntity>)

    @Query("SELECT * FROM rss_channels")
    fun getAllChannels(): List<RssChannelEntity>

}