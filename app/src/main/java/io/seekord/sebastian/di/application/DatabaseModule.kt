package io.seekord.sebastian.di.application

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import io.seekord.sebastian.data.db.SebastianDatabase
import javax.inject.Singleton


private const val DATABASE_NAME = "sebastian.db"


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideSebastianDatabase(application: Application): SebastianDatabase {
        return Room.databaseBuilder(application, SebastianDatabase::class.java, DATABASE_NAME)
                .build()
    }

}