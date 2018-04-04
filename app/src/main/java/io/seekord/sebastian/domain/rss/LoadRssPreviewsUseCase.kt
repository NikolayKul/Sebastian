package io.seekord.sebastian.domain.rss

import dagger.Reusable
import io.seekord.sebastian.domain.rss.models.RssPreview
import kotlinx.coroutines.experimental.delay
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@Reusable
class LoadRssPreviewsUseCase @Inject constructor() {

    suspend fun loadRssPreviews(): List<RssPreview> {
        delay(1000, TimeUnit.MILLISECONDS)
        return emptyList()
    }

}