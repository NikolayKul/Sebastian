package com.nikolaykul.sebastian.data.db.rss.feed

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface RssFeedDao {

    @Insert(onConflict = REPLACE)
    fun insert(feed: RssFeedEntity)

    @Insert(onConflict = REPLACE)
    fun insert(feeds: List<RssFeedEntity>)

    @Query("SELECT * FROM rss_feeds WHERE channel_id=:channelId")
    fun getFeeds(channelId: String): List<RssFeedEntity>

}