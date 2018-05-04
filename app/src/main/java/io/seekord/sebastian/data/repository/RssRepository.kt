package io.seekord.sebastian.data.repository

import dagger.Reusable
import io.seekord.sebastian.data.network.RssApi
import io.seekord.sebastian.data.repository.mappers.RssChannelFromDtoMapper
import io.seekord.sebastian.domain.channel.models.RssChannel
import io.seekord.sebastian.domain.rss.models.RssItem
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

    suspend fun getRssItems(): List<RssItem> {
        val mainDto = rssApi.getRssMain().await()
        return mainDto.items
                .map { RssItem(it.title, it.description, it.date) }
    }

    suspend fun getRssChannel(): RssChannel {
        val channelDto = rssApi.getChannel().await()
        return channelFromDtoMapper.map(channelDto)
    }

}