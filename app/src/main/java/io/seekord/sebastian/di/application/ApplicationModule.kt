package io.seekord.sebastian.di.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * @author NikolayKul
 */

@Module
class ApplicationModule(private val app: Application) {

    @Provides
    @Singleton
    @AppContext
    fun appContext(): Context = app.applicationContext

}


@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class AppContext
