package io.seekord.sebastian.data.repository.rss

import dagger.Reusable
import io.seekord.sebastian.data.store.rss.local.RssLocalStore
import io.seekord.sebastian.data.store.rss.remote.RssRemoteStore
import io.seekord.sebastian.domain.rss.models.RssChannel
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
        return remoteStore.fetchChannel()
                .also { localStore.saveChannel(it) }
    }

}