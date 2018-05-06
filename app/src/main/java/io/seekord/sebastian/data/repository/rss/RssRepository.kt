package io.seekord.sebastian.data.repository.rss

import dagger.Reusable
import io.seekord.sebastian.data.store.rss.remote.RssRemoteStore
import io.seekord.sebastian.domain.rss.models.RssChannel
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@Reusable
class RssRepository @Inject constructor(
        private val remoteStore: RssRemoteStore
) {

    suspend fun getRssChannel(): RssChannel {
        return remoteStore.fetchChannel()
    }

}