package io.seekord.sebastian.data.repository.mappers

import dagger.Reusable
import io.seekord.sebastian.data.network.models.RssChannelDto
import io.seekord.sebastian.domain.channel.models.RssChannel
import javax.inject.Inject

@Reusable
class RssChannelToDtoMapper @Inject constructor() : Mapper<RssChannel, RssChannelDto> {
    override fun map(input: RssChannel) = RssChannelDto(
            title = input.title,
            link = input.link,
            description = input.description
    )
}

@Reusable
class RssChannelFromDtoMapper @Inject constructor() : Mapper<RssChannelDto, RssChannel> {
    override fun map(input: RssChannelDto) = RssChannel(
            title = input.title.orEmpty(),
            link = input.link.orEmpty(),
            description = input.description.orEmpty()
    )
}
