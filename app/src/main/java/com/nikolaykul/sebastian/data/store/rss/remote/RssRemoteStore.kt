package com.nikolaykul.sebastian.data.store.rss.remote

import com.nikolaykul.sebastian.data.network.rss.RssApi
import com.nikolaykul.sebastian.data.store.rss.remote.mappers.RssChannelFromDtoMapper
import com.nikolaykul.sebastian.data.utils.ext.await
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RssRemoteStore @Inject constructor(
    private val api: RssApi,
    private val channelFromDtoMapper: RssChannelFromDtoMapper
) {

    suspend fun fetchChannel() = api.getChannel().await()
        .let(channelFromDtoMapper::map)

}
