package io.seekord.sebastian.data.repository

import dagger.Reusable
import io.seekord.sebastian.data.network.RssApi
import io.seekord.sebastian.utils.network.toDeferred
import kotlinx.coroutines.experimental.Deferred
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@Reusable
class RssRepository @Inject constructor(
        private val rssApi: RssApi
) {

    fun getRssPreviews(): Deferred<Any> = rssApi.getRssPreviews().toDeferred()

}