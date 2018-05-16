package com.nikolaykul.sebastian.domain.rss

import com.nikolaykul.sebastian.data.repository.rss.RssRepository
import com.nikolaykul.sebastian.domain.UseCase
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetFeedUseCase @Inject constructor(
    private val repository: RssRepository
) : UseCase<FeedId, RssFeed?> {

    override suspend fun execute(params: FeedId): RssFeed? = TODO("not implemented")

}

class FeedId(private val id: String)
