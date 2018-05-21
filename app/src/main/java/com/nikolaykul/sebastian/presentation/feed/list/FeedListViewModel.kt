package com.nikolaykul.sebastian.presentation.feed.list

import android.support.annotation.VisibleForTesting
import com.nikolaykul.sebastian.domain.NoNetworkException
import com.nikolaykul.sebastian.domain.rss.GetChannelUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.SCREEN_FEED_DETAILS
import com.nikolaykul.sebastian.presentation.base.BaseViewModel
import com.nikolaykul.sebastian.utils.common.CoroutineContextProvider.UI
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.experimental.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FeedListViewModel @Inject constructor(
        private val getChannelUseCase: GetChannelUseCase,
        private val router: Router
) : BaseViewModel() {
    private val stateRelay = BehaviorSubject.createDefault(FeedListState())

    fun observeState(): Flowable<FeedListState> = stateRelay.toFlowable(BackpressureStrategy.LATEST)

    fun loadChannel() {
        launch(UI) {
            mutateState(isLoading = true)

            val channel = try {
                getChannelUseCase.execute()
            } catch (e: NoNetworkException) {
                newState(FeedListState(error = e))
                return@launch
            }

            newState(FeedListState(feeds = channel.feeds))

        }.attachToLifecycle()
    }

    fun onFeedClicked(feed: RssFeed) {
        router.navigateTo(SCREEN_FEED_DETAILS, feed.id)
    }

    private fun lastState() = stateRelay.value

    private fun mutateState(
            isLoading: Boolean = lastState().isLoading,
            error: Exception? = lastState().error,
            feeds: List<RssFeed>? = lastState().feeds
    ) {
        newState(FeedListState(isLoading, error, feeds))
    }

    private fun newState(newState: FeedListState) {
        stateRelay.onNext(newState)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun setState(state: FeedListState) {
        newState(state)
    }

}
