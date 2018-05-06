package io.seekord.sebastian.data.store.rss.local.mappers

import dagger.Reusable
import io.seekord.sebastian.data.db.rss.channel.RssChannelEntity
import io.seekord.sebastian.data.db.rss.feed.RssFeedEntity
import io.seekord.sebastian.data.store.rss.local.mappers.RssChannelFromEntityMapper.Params
import io.seekord.sebastian.domain.rss.models.RssChannel
import io.seekord.sebastian.utils.common.Mapper
import javax.inject.Inject

@Reusable
class RssChannelToEntityMapper @Inject constructor() : Mapper<RssChannel, RssChannelEntity> {
    override fun map(input: RssChannel) = RssChannelEntity(
            id = "id_${input.link}",    // TODO: use a factory to generate `id`
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
            title = input.channelEntity.title,
            link = input.channelEntity.link,
            description = input.channelEntity.description,
            feeds = input.feedEntities.map(feedFromEntityMapper::map)
    )

    class Params(val channelEntity: RssChannelEntity, val feedEntities: List<RssFeedEntity>)
}
