package io.seekord.sebastian.data.network

import retrofit2.Call
import retrofit2.http.GET

/**
 * @author NikolayKul
 */

interface RssApi {

    @GET("interesting")
    fun getRssPreviews(): Call<Any>

}