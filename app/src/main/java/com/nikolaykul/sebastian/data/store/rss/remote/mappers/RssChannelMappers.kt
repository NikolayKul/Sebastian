package com.nikolaykul.sebastian.data.store.rss.remote.mappers

import com.nikolaykul.sebastian.data.network.rss.models.RssChannelDto
import com.nikolaykul.sebastian.data.store.rss.remote.factory.RssChannelIdFactory
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import com.nikolaykul.sebastian.utils.common.Mapper
import dagger.Reusable
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
        id = RssChannelIdFactory.createId(input),
        title = input.title.orEmpty(),
        link = input.link.orEmpty(),
        description = input.description.orEmpty(),
        feeds = input.feeds?.map(rssFeedFromDtoMapper::map) ?: emptyList()
    )
}
