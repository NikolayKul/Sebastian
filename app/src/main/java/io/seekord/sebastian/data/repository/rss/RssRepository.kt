package io.seekord.sebastian.data.repository.rss

import dagger.Reusable
import io.seekord.sebastian.data.network.rss.RssApi
import io.seekord.sebastian.data.repository.rss.mappers.RssChannelFromDtoMapper
import io.seekord.sebastian.domain.rss.models.RssChannel
import io.seekord.sebastian.utils.network.await
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@Reusable
class RssRepository @Inject constructor(
        private val rssApi: RssApi,
        private val channelFromDtoMapper: RssChannelFromDtoMapper
) {

    suspend fun getRssChannel(): RssChannel {
        val channelDto = rssApi.getChannel().await()
        return channelFromDtoMapper.map(channelDto)
    }

}