package com.nikolaykul.sebastian.domain.rss

import com.nikolaykul.sebastian.data.repository.rss.RssRepository
import com.nikolaykul.sebastian.domain.UseCaseNoParams
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetChannelUseCase @Inject constructor(
    private val repository: RssRepository
) : UseCaseNoParams<RssChannel> {
    override suspend fun execute(): RssChannel = repository.getRssChannel()
}
