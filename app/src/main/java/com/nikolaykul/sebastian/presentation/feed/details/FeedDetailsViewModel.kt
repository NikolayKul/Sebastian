package com.nikolaykul.sebastian.presentation.feed.details

import com.nikolaykul.sebastian.domain.rss.FeedId
import com.nikolaykul.sebastian.domain.rss.GetFeedUseCase
import com.nikolaykul.sebastian.presentation.base.StatefulViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedDetailsViewModel @Inject constructor(
    private val feedId: FeedId,
    private val getFeedUseCase: GetFeedUseCase
) : StatefulViewModel<FeedDetailsState>() {
    override val defaultState = FeedDetailsState()

    fun loadFeed() {
        launch {
            val feed = getFeedUseCase.execute(feedId)
            nextState { FeedDetailsState(feed) }
        }
    }

}