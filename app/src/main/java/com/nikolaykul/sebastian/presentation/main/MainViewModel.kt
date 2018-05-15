package com.nikolaykul.sebastian.presentation.main

import android.support.annotation.VisibleForTesting
import com.nikolaykul.sebastian.domain.NoNetworkException
import com.nikolaykul.sebastian.domain.rss.GetChannelUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.base.BaseViewModel
import com.nikolaykul.sebastian.utils.common.CoroutineContextProvider.UI
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val getChannelUseCase: GetChannelUseCase
) : BaseViewModel() {
    private val stateRelay = BehaviorSubject.createDefault(MainState())

    fun observeState(): Flowable<MainState> = stateRelay.toFlowable(BackpressureStrategy.LATEST)

    fun loadChannel() {
        launch(UI) {
            mutateState(isLoading = true)

            val channel = try {
                getChannelUseCase.execute()
            } catch (e: NoNetworkException) {
                newState(MainState(error = e))
                return@launch
            }

            newState(MainState(feeds = channel.feeds))

        }.attachToLifecycle()
    }

    fun onFeedClicked(feed: RssFeed) {
        Timber.d("Feed was clicked: $feed")
    }

    private fun lastState() = stateRelay.value

    private fun mutateState(
            isLoading: Boolean = lastState().isLoading,
            error: Exception? = lastState().error,
            feeds: List<RssFeed>? = lastState().feeds
    ) {
        newState(MainState(isLoading, error, feeds))
    }

    private fun newState(newState: MainState) {
        stateRelay.onNext(newState)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun setState(state: MainState) {
        newState(state)
    }

}
