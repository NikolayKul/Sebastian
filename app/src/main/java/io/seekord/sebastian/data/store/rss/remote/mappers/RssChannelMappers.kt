package io.seekord.sebastian.data.store.rss.remote.mappers

import dagger.Reusable
import io.seekord.sebastian.data.network.rss.models.RssChannelDto
import io.seekord.sebastian.domain.rss.models.RssChannel
import io.seekord.sebastian.utils.common.Mapper
import javax.inject.Inject

@Reusable
class RssChannelToDtoMapper @Inject constructor(
        private val rssFeedToDtoMapper: RssFeedToDtoMapper
) : Mapper<RssChannel, RssChannelDto> {
    override fun map(input: RssChannel) = RssChannelDto(
            title = input.title,
            link = input.link,
            description = input.description,
            feeds = input.feeds.map(rssFeedToDtoMapper::map)
    )
}


@Reusable
class RssChannelFromDtoMapper @Inject constructor(
        private val rssFeedFromDtoMapper: RssFeedFromDtoMapper
) : Mapper<RssChannelDto, RssChannel> {
    override fun map(input: RssChannelDto) = RssChannel(
            title = input.title.orEmpty(),
            link = input.link.orEmpty(),
            description = input.description.orEmpty(),
            feeds = input.feeds?.map(rssFeedFromDtoMapper::map) ?: emptyList()
    )
}
