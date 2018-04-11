package io.seekord.sebastian.data.repository

import dagger.Reusable
import io.seekord.sebastian.data.models.RssInterestingDto
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

    suspend fun getRssInteresting(): RssInterestingDto = rssApi.getRssInteresting().await()

}