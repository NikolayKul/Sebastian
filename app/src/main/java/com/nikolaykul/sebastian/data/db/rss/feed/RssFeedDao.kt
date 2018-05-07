package com.nikolaykul.sebastian.data.db.rss.feed

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface RssFeedDao {

    @Insert(onConflict = REPLACE)
    fun insert(feed: RssFeedEntity)

    @Insert(onConflict = REPLACE)
    fun insert(feeds: List<RssFeedEntity>)

}