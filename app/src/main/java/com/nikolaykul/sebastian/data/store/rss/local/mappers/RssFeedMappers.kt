package com.nikolaykul.sebastian.data.store.rss.local.mappers

import com.nikolaykul.sebastian.data.db.rss.feed.RssFeedEntity
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.common.Mapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RssFeedToEntityMapper @Inject constructor() : Mapper<RssFeedToEntityMapper.Params, RssFeedEntity> {

    override fun map(input: Params) = RssFeedEntity(
            id = input.feed.id,
            channelId = input.channelId,
            title = input.feed.title,
            description = input.feed.description,
            date = input.feed.date
    )

    class Params(val channelId: String, val feed: RssFeed)
}

@Reusable
class RssFeedFromEntityMapper @Inject constructor() : Mapper<RssFeedEntity, RssFeed> {
    override fun map(input: RssFeedEntity) = RssFeed(
            id = input.id,
            title = input.title,
            description = input.description,
            date = input.date
    )
}
