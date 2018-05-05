package io.seekord.sebastian.domain.channel

import dagger.Reusable
import io.seekord.sebastian.data.repository.RssRepository
import io.seekord.sebastian.domain.UseCaseNoParams
import io.seekord.sebastian.domain.channel.models.RssChannel
import javax.inject.Inject

@Reusable
class GetChannelUseCase @Inject constructor(
        private val repository: RssRepository
) : UseCaseNoParams<RssChannel> {

    override suspend fun execute() = repository.getRssChannel()

}
