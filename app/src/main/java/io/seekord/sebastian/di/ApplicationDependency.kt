package io.seekord.sebastian.di

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by nikolay
 */

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent


@Module
class ApplicationModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app.applicationContext

}