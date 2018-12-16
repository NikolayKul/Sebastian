package com.nikolaykul.sebastian.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.nikolaykul.sebastian.data.db.rss.channel.RssChannelDao
import com.nikolaykul.sebastian.data.db.rss.channel.RssChannelEntity
import com.nikolaykul.sebastian.data.db.rss.feed.RssFeedDao
import com.nikolaykul.sebastian.data.db.rss.feed.RssFeedEntity

@Database(
    version = 1,
    entities = [RssChannelEntity::class, RssFeedEntity::class]
)
@TypeConverters(Converters::class)
abstract class SebastianDatabase : RoomDatabase() {

    abstract fun channelDao(): RssChannelDao

    abstract fun feedDao(): RssFeedDao

}