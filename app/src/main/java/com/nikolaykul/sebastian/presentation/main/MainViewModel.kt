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
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val getChannelUseCase: GetChannelUseCase
) : BaseViewModel() {
    private val stateRelay = BehaviorSubject.createDefault(MainState())

    fun observeState(): Flowable<MainState> = stateRelay.toFlowable(BackpressureStrategy.LATEST)

    fun loadChannel() {
        launch(UI) {
            mutateState(isLoading = true)
            try {
                val channel = getChannelUseCase.execute()
                newState(MainState(feeds = channel.feeds))
            } catch (e: NoNetworkException) {
                newState(MainState(error = e))
            }
        }.attachToLifecycle()
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
