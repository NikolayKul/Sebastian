package io.seekord.sebastian.data.db.rss

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.seekord.sebastian.data.db.rss.models.RssFeedEntity

@Dao
interface RssFeedDao {

    @Insert(onConflict = REPLACE)
    fun insertOrUpdate(feed: RssFeedEntity)

    @Insert(onConflict = REPLACE)
    fun insertOrUpdate(feeds: List<RssFeedEntity>)

}