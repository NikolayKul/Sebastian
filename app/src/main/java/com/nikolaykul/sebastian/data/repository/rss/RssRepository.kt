package com.nikolaykul.sebastian.data.repository.rss

import com.nikolaykul.sebastian.data.store.rss.local.RssLocalStore
import com.nikolaykul.sebastian.data.store.rss.remote.RssRemoteStore
import com.nikolaykul.sebastian.domain.NoNetworkException
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.common.NetworkManager
import dagger.Reusable
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@Reusable
class RssRepository @Inject constructor(
    private val networkManager: NetworkManager,
    private val remoteStore: RssRemoteStore,
    private val localStore: RssLocalStore
) {

    suspend fun getRssChannel(): RssChannel {
        val channels = localStore.getAllChannels()
        if (channels.isNotEmpty()) {
            return channels[0]
        }

        if (!networkManager.isNetworkAvailable()) {
            throw NoNetworkException()
        }

        return remoteStore.fetchChannel()
            .also { localStore.saveChannel(it) }
    }

    suspend fun getFeed(id: String): RssFeed? = localStore.findFeed(id)

}