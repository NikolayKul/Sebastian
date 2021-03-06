package com.nikolaykul.sebastian.di.application

import android.app.Application
import android.arch.persistence.room.Room
import com.nikolaykul.sebastian.data.db.SebastianDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton


private const val DATABASE_NAME = "sebastian.db"


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun sebastianDatabase(application: Application): SebastianDatabase {
        return Room.databaseBuilder(application, SebastianDatabase::class.java, DATABASE_NAME)
                .build()
    }

    @Provides
    @Reusable
    fun channelDao(database: SebastianDatabase) = database.channelDao()

    @Provides
    @Reusable
    fun feedDao(database: SebastianDatabase) = database.feedDao()

}