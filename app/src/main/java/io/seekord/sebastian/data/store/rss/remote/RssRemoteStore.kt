package io.seekord.sebastian.data.store.rss.remote

import dagger.Reusable
import io.seekord.sebastian.data.network.rss.RssApi
import io.seekord.sebastian.data.store.rss.remote.mappers.RssChannelFromDtoMapper
import io.seekord.sebastian.utils.network.await
import javax.inject.Inject

@Reusable
class RssRemoteStore @Inject constructor(
        private val api: RssApi,
        private val channelFromDtoMapper: RssChannelFromDtoMapper
) {

    suspend fun fetchChannel() = api.getChannel().await()
            .let(channelFromDtoMapper::map)

}
