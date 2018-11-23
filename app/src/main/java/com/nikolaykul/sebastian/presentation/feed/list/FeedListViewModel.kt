package com.nikolaykul.sebastian.presentation.feed.list

import com.nikolaykul.sebastian.domain.NoNetworkException
import com.nikolaykul.sebastian.domain.rss.GetChannelUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.SCREEN_FEED_DETAILS
import com.nikolaykul.sebastian.presentation.base.StatefulViewModel
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FeedListViewModel @Inject constructor(
    private val getChannelUseCase: GetChannelUseCase,
    private val router: Router
) : StatefulViewModel<FeedListState>() {
    override val defaultState = FeedListState()

    fun loadChannel() {
        launch {
            mutateState { it.copy(isLoading = true) }

            val channel = try {
                getChannelUseCase.execute()
            } catch (e: NoNetworkException) {
                newState(FeedListState(error = e))
                return@launch
            }

            newState(FeedListState(feeds = channel.feeds))
        }
    }

    fun onFeedClicked(feed: RssFeed) {
        router.navigateTo(SCREEN_FEED_DETAILS, feed.id)
    }

}
