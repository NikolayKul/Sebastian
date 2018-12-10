package com.nikolaykul.sebastian.presentation.feed.details

import com.nikolaykul.sebastian.domain.rss.FeedId
import com.nikolaykul.sebastian.domain.rss.GetFeedUseCase
import com.nikolaykul.sebastian.presentation.base.StatefulViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

class FeedDetailsViewModel @AssistedInject constructor(
    @Assisted private val feedId: FeedId,
    private val getFeedUseCase: GetFeedUseCase
) : StatefulViewModel<FeedDetailsState>() {
    override val initState = FeedDetailsState()

    fun loadFeed() {
        launch {
            val feed = getFeedUseCase.execute(feedId)
            nextState { FeedDetailsState(feed) }
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(feedId: FeedId): FeedDetailsViewModel
    }
}