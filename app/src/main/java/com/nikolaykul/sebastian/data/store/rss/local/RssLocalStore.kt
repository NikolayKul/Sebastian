package com.nikolaykul.sebastian.data.store.rss.local

import com.nikolaykul.sebastian.data.db.rss.channel.RssChannelDao
import com.nikolaykul.sebastian.data.db.rss.feed.RssFeedDao
import com.nikolaykul.sebastian.data.store.rss.local.mappers.RssChannelToEntityMapper
import com.nikolaykul.sebastian.data.store.rss.local.mappers.RssFeedToEntityMapper
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import com.nikolaykul.sebastian.utils.common.CoroutineContextProvider.IO
import dagger.Reusable
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
                .map { RssFeedToEntityMapper.Params(channelId = channel.id, feed = it) }
                .map { feedToEntityMapper.map(it) }
                .let { feedDao.insert(it) }
    }

}
