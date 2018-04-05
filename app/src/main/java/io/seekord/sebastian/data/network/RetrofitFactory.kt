package io.seekord.sebastian.data.network

import dagger.Reusable
import io.seekord.sebastian.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

/**
 * @author NikolayKul
 */


private const val GEEKTIMES_URL = "https://geektimes.ru/rss"


@Reusable
class RetrofitFactory @Inject constructor() {

    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(GEEKTIMES_URL)
                .client(createClient())
                .build()
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(createLoggingInterceptor())
                .build()
    }

    private fun createLoggingInterceptor(): Interceptor {
        val level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        return HttpLoggingInterceptor { Timber.tag("[OkHttp]").d(it) }
                .setLevel(level)
    }

}
