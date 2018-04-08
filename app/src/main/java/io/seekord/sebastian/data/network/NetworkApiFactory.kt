package io.seekord.sebastian.data.network

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Reusable
import io.seekord.sebastian.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import javax.inject.Inject

/**
 * @author NikolayKul
 */


private const val GEEKTIMES_URL = "https://geektimes.ru/rss/"


@Reusable
class NetworkApiFactory @Inject constructor() {

    fun create(): RssApi = createRetrofit().create(RssApi::class.java)

    private fun createRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(GEEKTIMES_URL)
            .client(createClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(TikXmlConverterFactory.create(
                    TikXml.Builder()
                            .exceptionOnUnreadXml(false)
                            .build()))
            .build()

    private fun createClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor())
            .build()

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
