package io.seekord.sebastian.data.db.rss.channel

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.seekord.sebastian.data.db.rss.channel.RssChannelEntity

@Dao
interface RssChannelDao {

    @Insert(onConflict = REPLACE)
    fun insertOrUpdate(channel: RssChannelEntity)

    @Insert(onConflict = REPLACE)
    fun insertOrUpdate(channels: List<RssChannelEntity>)

}