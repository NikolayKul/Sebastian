package io.seekord.sebastian.domain.rss

import dagger.Reusable
import io.seekord.sebastian.data.repository.RssRepository
import io.seekord.sebastian.domain.rss.models.RssPreview
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@Reusable
class GetRssPreviewsUseCase @Inject constructor(
        private val repository: RssRepository
) {

    suspend fun getPssPreviews(): List<RssPreview> = repository.getRssPreviews()

}