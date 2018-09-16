package com.nikolaykul.sebastian.presentation.feed.details

import com.nikolaykul.sebastian.domain.rss.FeedId
import com.nikolaykul.sebastian.domain.rss.GetFeedUseCase
import com.nikolaykul.sebastian.presentation.base.StatefulViewModel
import com.nikolaykul.sebastian.utils.common.CoroutineContextProvider.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class FeedDetailsViewModel @Inject constructor(
    private val feedId: FeedId,
    private val getFeedUseCase: GetFeedUseCase
) : StatefulViewModel<FeedDetailsState>() {

    fun loadFeed() {
        launch(UI) {
            val feed = getFeedUseCase.execute(feedId)
            newState(FeedDetailsState(feed))
        }.attachToLifecycle()
    }

}