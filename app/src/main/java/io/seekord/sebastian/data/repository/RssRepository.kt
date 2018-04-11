package io.seekord.sebastian.data.repository

import dagger.Reusable
import io.seekord.sebastian.data.models.RssPreviewDto
import io.seekord.sebastian.data.network.RssApi
import io.seekord.sebastian.utils.network.await
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@Reusable
class RssRepository @Inject constructor(
        private val rssApi: RssApi
) {

    suspend fun getRssPreviews(): RssPreviewDto = rssApi.getRssPreviews().await()

}