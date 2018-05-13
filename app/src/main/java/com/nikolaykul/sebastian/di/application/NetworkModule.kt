package com.nikolaykul.sebastian.di.application

import android.app.Application
import com.nikolaykul.sebastian.BuildConfig
import com.nikolaykul.sebastian.data.network.rss.RssApi
import com.nikolaykul.sebastian.utils.debug.StethoUtils
import com.nikolaykul.sebastian.utils.network.TikXmlFactory
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import javax.inject.Qualifier
import javax.inject.Singleton


private const val GEEKTIMES_BASE_URL = "https://geektimes.com/rss/"
private const val CACHE_MAX_SIZE = 1024 * 1024 * 10L // 10 MB


/**
 * Restrict Kotlin to generate an invariant [Set] method argument for Java.
 *
 * By default Kotlin generates a covariant [Collection] for method arguments that are used from Java
 * but Dagger looks for an invariant one.
 */
private typealias InvariantSet<T> = Set<@JvmSuppressWildcards T>


/**
 * Qualifier for [OkHttpClient.interceptors]
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
private annotation class AppInterceptor


/**
 * Qualifier for [OkHttpClient.networkInterceptors]
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
private annotation class NetworkInterceptor


/**
 * Module that provides an [RssApi] and it's dependencies
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun rssApi(retrofit: Retrofit): RssApi = retrofit.create(RssApi::class.java)

    @Provides
    fun retrofit(client: OkHttpClient,
                 converterFactories: InvariantSet<Converter.Factory>): Retrofit {
        return Retrofit.Builder()
                .baseUrl(GEEKTIMES_BASE_URL)
                .client(client)
                .apply { converterFactories().addAll(converterFactories) }
                .build()
    }

    @Provides
    fun client(cache: Cache,
               @AppInterceptor appInterceptors: InvariantSet<Interceptor>,
               @NetworkInterceptor networkInterceptors: InvariantSet<Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .apply { interceptors().addAll(appInterceptors) }
                .apply { networkInterceptors().addAll(networkInterceptors) }
                .build()
    }

    @Provides
    fun cache(application: Application): Cache = Cache(application.cacheDir, CACHE_MAX_SIZE)

    // converters

    @Provides
    @IntoSet
    fun converterFactoryScalars(): Converter.Factory = ScalarsConverterFactory.create()

    @Provides
    @IntoSet
    fun converterFactoryXml(): Converter.Factory = TikXmlFactory.create()
            .let { TikXmlConverterFactory.create(it) }

    // app interceptors

    @Provides
    @IntoSet
    @AppInterceptor
    fun interceptorLogging(): Interceptor {
        val level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.HEADERS
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        return HttpLoggingInterceptor { Timber.tag("[OkHttp]").d(it) }
                .setLevel(level)
    }

    // network interceptors

    @Provides
    @IntoSet
    @NetworkInterceptor
    fun interceptorStetho(): Interceptor = StethoUtils.provideInterceptor()

}
