package io.seekord.sebastian.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import io.seekord.sebastian.data.db.rss.RssChannelDao
import io.seekord.sebastian.data.db.rss.RssFeedDao
import io.seekord.sebastian.data.db.rss.models.RssChannelEntity
import io.seekord.sebastian.data.db.rss.models.RssFeedEntity

@Database(
        version = 1,
        entities = [RssChannelEntity::class, RssFeedEntity::class])
@TypeConverters(Converters::class)
abstract class SebastianDatabase : RoomDatabase() {

    abstract fun rssChannelDao(): RssChannelDao

    abstract fun rssFeedDao(): RssFeedDao

}