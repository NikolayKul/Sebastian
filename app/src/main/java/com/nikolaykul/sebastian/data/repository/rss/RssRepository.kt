package com.nikolaykul.sebastian.data.repository.rss

import com.nikolaykul.sebastian.data.store.rss.local.RssLocalStore
import com.nikolaykul.sebastian.data.store.rss.remote.RssRemoteStore
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import dagger.Reusable
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@Reusable
class RssRepository @Inject constructor(
        private val remoteStore: RssRemoteStore,
        private val localStore: RssLocalStore
) {

    suspend fun getRssChannel(): RssChannel {
        val channels = localStore.getAllChannels()
        return if (channels.isNotEmpty()) {
            channels[0]
        } else {
            remoteStore.fetchChannel()
                    .also { localStore.saveChannel(it) }
        }
    }

}