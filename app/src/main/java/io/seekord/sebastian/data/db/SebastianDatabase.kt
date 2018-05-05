package io.seekord.sebastian.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import io.seekord.sebastian.data.db.rss.RssChannelDao
import io.seekord.sebastian.data.db.rss.models.RssChannelEntity

@Database(entities = [RssChannelEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class SebastianDatabase : RoomDatabase() {

    abstract fun rssChannelDao(): RssChannelDao

}