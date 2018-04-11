package io.seekord.sebastian.domain.rss

import dagger.Reusable
import io.seekord.sebastian.data.repository.RssRepository
import io.seekord.sebastian.domain.rss.models.RssPreview
import timber.log.Timber
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@Reusable
class LoadRssPreviewsUseCase @Inject constructor(
        private val repository: RssRepository
) {

    suspend fun loadRssPreviews(): List<RssPreview> {
        val interesting = repository.getRssInteresting()
        Timber.d("result: %s", interesting)

        return emptyList()
    }

}