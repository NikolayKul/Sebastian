package io.seekord.sebastian.data.repository.mappers

import dagger.Reusable
import io.seekord.sebastian.data.network.models.RssFeedDto
import io.seekord.sebastian.domain.channel.models.RssFeed
import javax.inject.Inject

@Reusable
class RssFeedToDtoMapper @Inject constructor() : Mapper<RssFeed, RssFeedDto> {
    override fun map(input: RssFeed) = RssFeedDto(
            id = input.id,
            title = input.title,
            description = input.description,
            date = input.date
    )
}

@Reusable
class RssFeedFromDtoMapper @Inject constructor() : Mapper<RssFeedDto, RssFeed> {
    override fun map(input: RssFeedDto) = RssFeed(
            id = input.id.orEmpty(),
            title = input.title.orEmpty(),
            description = input.description.orEmpty(),
            date = input.date
    )
}