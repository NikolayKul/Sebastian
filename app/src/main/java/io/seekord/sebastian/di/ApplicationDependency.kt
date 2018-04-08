package io.seekord.sebastian.di

import android.app.Application
import android.content.Context
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import io.seekord.sebastian.BuildConfig
import io.seekord.sebastian.data.network.RssApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber
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
    fun applicationContext(): Context = app.applicationContext

}


@Module
class RouterModule {
    private val cicerone = Cicerone.create()

    @Provides
    @Singleton
    fun navigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun router(): Router = cicerone.router

}


private const val GEEKTIMES_URL = "https://geektimes.ru/rss/"

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun rssApi(retrofit: Retrofit): RssApi = retrofit.create(RssApi::class.java)

    @Provides
    fun retrofit(client: OkHttpClient,
                 converterFactories: Set<@JvmSuppressWildcards Converter.Factory>): Retrofit {
        return Retrofit.Builder()
                .baseUrl(GEEKTIMES_URL)
                .client(client)
                .apply { converterFactories().addAll(converterFactories) }
                .build()
    }

    @Provides
    fun client(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient = OkHttpClient.Builder()
            .apply { interceptors().addAll(interceptors) }
            .build()

    // converters

    @Provides
    @IntoSet
    fun converterFactoryScalars(): Converter.Factory = ScalarsConverterFactory.create()

    @Provides
    @IntoSet
    fun converterFactoryXml(): Converter.Factory = TikXml.Builder()
            .exceptionOnUnreadXml(false)
            .build()
            .let { TikXmlConverterFactory.create(it) }

    // interceptors

    @Provides
    @IntoSet
    fun interceptorLogging(): Interceptor {
        val level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        return HttpLoggingInterceptor { Timber.tag("[OkHttp]").d(it) }
                .setLevel(level)
    }

}


@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class AppContext