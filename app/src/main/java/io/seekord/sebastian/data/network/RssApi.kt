package io.seekord.sebastian.data.network

import io.seekord.sebastian.data.network.models.RssChannelDto
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author NikolayKul
 */

interface RssApi {

    @GET("interesting")
    fun getChannel(): Call<RssChannelDto>

}