package com.nikolaykul.sebastian.presentation.feed.list

import com.nikolaykul.sebastian.domain.rss.GetChannelUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.SCREEN_FEED_DETAILS
import com.nikolaykul.sebastian.presentation.base.StatefulViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FeedListViewModel @Inject constructor(
    private val getChannelUseCase: GetChannelUseCase,
    private val router: Router
) : StatefulViewModel<FeedListState>() {
    override val initState = FeedListState()
    override val coroutineContext = super.coroutineContext + CoroutineExceptionHandler { _, t ->
        nextState { FeedListState(error = t) }
    }

    fun loadChannel() {
        launch {
            nextState { it.copy(isLoading = true) }

            val channel = getChannelUseCase.execute()

            nextState { FeedListState(feeds = channel.feeds) }
        }
    }

    fun onFeedClicked(feed: RssFeed) {
        router.navigateTo(SCREEN_FEED_DETAILS, feed.id)
    }

}
