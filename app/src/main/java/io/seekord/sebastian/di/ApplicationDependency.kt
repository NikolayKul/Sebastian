package io.seekord.sebastian.di

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import io.seekord.sebastian.data.network.RetrofitFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * @author NikolayKul
 */

@Singleton
@Component(modules = [ApplicationModule::class, RouterModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun plusActivityComponent(module: ActivityModule): ActivityComponent
}


@Module
class ApplicationModule(private val app: Application) {

    @Provides
    @Singleton
    @AppContext
    fun provideApplicationContext(): Context = app.applicationContext

}


@Module
class RouterModule {
    private val cicerone = Cicerone.create()

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun provideRouter(): Router = cicerone.router

}


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(factory: RetrofitFactory) = factory.createRetrofit()

}


@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class AppContext