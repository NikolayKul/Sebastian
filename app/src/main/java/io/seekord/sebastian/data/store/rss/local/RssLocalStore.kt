package io.seekord.sebastian.data.store.rss.local

import dagger.Reusable
import io.seekord.sebastian.data.db.rss.channel.RssChannelDao
import io.seekord.sebastian.data.db.rss.feed.RssFeedDao
import io.seekord.sebastian.data.store.rss.local.mappers.RssChannelToEntityMapper
import io.seekord.sebastian.data.store.rss.local.mappers.RssFeedToEntityMapper
import io.seekord.sebastian.domain.rss.models.RssChannel
import io.seekord.sebastian.utils.common.CoroutineContextProvider.IO
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject

@Reusable
class RssLocalStore @Inject constructor(
        private val channelDao: RssChannelDao,
        private val feedDao: RssFeedDao,
        private val channelToEntityMapper: RssChannelToEntityMapper,
        private val feedToEntityMapper: RssFeedToEntityMapper
) {

    suspend fun saveChannel(channel: RssChannel) = withContext(IO) {
        channelToEntityMapper.map(channel)
                .also { channelDao.insert(it) }
        channel.feeds
                .map { RssFeedToEntityMapper.Params(channelId = "id_${channel.link}", feed = it) }
                .map { feedToEntityMapper.map(it) }
                .let { feedDao.insert(it) }
    }

}
