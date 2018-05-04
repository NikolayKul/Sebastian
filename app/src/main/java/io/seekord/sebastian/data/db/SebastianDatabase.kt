package io.seekord.sebastian.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import io.seekord.sebastian.data.db.rss.RssItemDao
import io.seekord.sebastian.data.db.rss.models.RssItemEntity

@Database(entities = [RssItemEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class SebastianDatabase : RoomDatabase() {

    abstract fun rssItemDao(): RssItemDao

}