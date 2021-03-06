package com.nikolaykul.sebastian.data.network.rss

import com.nikolaykul.sebastian.data.network.rss.models.RssChannelDto
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author NikolayKul
 */

interface RssApi {

    @GET("interesting")
    fun getChannel(): Call<RssChannelDto>

}