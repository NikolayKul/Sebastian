package com.nikolaykul.sebastian.data.store.rss.remote.mappers

import com.nikolaykul.sebastian.data.network.rss.models.RssFeedDto
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.common.Mapper
import dagger.Reusable
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