package io.seekord.sebastian.domain.rss

import dagger.Reusable
import io.seekord.sebastian.data.repository.RssRepository
import io.seekord.sebastian.domain.rss.models.RssItem
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@Reusable
class GetRssItemsUseCase @Inject constructor(
        private val repository: RssRepository
) {

    suspend fun getPssItems(): List<RssItem> = repository.getRssItems()

}