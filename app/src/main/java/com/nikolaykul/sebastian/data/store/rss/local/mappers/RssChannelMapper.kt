package com.nikolaykul.sebastian.data.store.rss.local.mappers

import com.nikolaykul.sebastian.data.db.rss.channel.RssChannelEntity
import com.nikolaykul.sebastian.data.db.rss.feed.RssFeedEntity
import com.nikolaykul.sebastian.data.store.rss.local.mappers.RssChannelFromEntityMapper.Params
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import com.nikolaykul.sebastian.utils.common.Mapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RssChannelToEntityMapper @Inject constructor() : Mapper<RssChannel, RssChannelEntity> {
    override fun map(input: RssChannel) = RssChannelEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            link = input.link
    )
}


@Reusable
class RssChannelFromEntityMapper @Inject constructor(
        private val feedFromEntityMapper: RssFeedFromEntityMapper
) : Mapper<Params, RssChannel> {

    override fun map(input: Params) = RssChannel(
            id = input.channelEntity.id,
            title = input.channelEntity.title,
            link = input.channelEntity.link,
            description = input.channelEntity.description,
            feeds = input.feedEntities.map(feedFromEntityMapper::map)
    )

    class Params(val channelEntity: RssChannelEntity, val feedEntities: List<RssFeedEntity>)
}
