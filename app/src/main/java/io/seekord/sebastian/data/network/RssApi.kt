package io.seekord.sebastian.data.network

import io.seekord.sebastian.data.models.RssInterestingDto
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author NikolayKul
 */

interface RssApi {

    @GET("interesting")
    fun getRssInteresting(): Call<RssInterestingDto>

}