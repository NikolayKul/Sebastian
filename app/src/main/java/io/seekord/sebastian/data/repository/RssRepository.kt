package io.seekord.sebastian.data.repository

import dagger.Reusable
import io.seekord.sebastian.data.network.RssApi
import io.seekord.sebastian.domain.rss.models.RssPreview
import io.seekord.sebastian.utils.network.await
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@Reusable
class RssRepository @Inject constructor(
        private val rssApi: RssApi
) {

    suspend fun getRssPreviews(): List<RssPreview> {
        val interesting = rssApi.getRssInteresting().await()
        return interesting.previews
                .map { RssPreview(it.title, it.description, it.date) }
    }

}