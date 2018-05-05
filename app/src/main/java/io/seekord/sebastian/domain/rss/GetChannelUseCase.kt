package io.seekord.sebastian.domain.rss

import dagger.Reusable
import io.seekord.sebastian.data.repository.rss.RssRepository
import io.seekord.sebastian.domain.UseCaseNoParams
import io.seekord.sebastian.domain.rss.models.RssChannel
import javax.inject.Inject

@Reusable
class GetChannelUseCase @Inject constructor(
        private val repository: RssRepository
) : UseCaseNoParams<RssChannel> {

    override suspend fun execute() = repository.getRssChannel()

}
