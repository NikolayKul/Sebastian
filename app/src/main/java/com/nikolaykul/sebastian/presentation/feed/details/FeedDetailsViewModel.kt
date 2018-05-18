package com.nikolaykul.sebastian.presentation.feed.details

import android.support.annotation.VisibleForTesting
import com.nikolaykul.sebastian.domain.rss.FeedId
import com.nikolaykul.sebastian.domain.rss.GetFeedUseCase
import com.nikolaykul.sebastian.presentation.base.BaseViewModel
import com.nikolaykul.sebastian.utils.common.CoroutineContextProvider.UI
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class FeedDetailsViewModel @Inject constructor(
    private val feedId: FeedId,
    private val getFeedUseCase: GetFeedUseCase
) : BaseViewModel() {
    private val stateRelay = BehaviorSubject.create<FeedDetailsState>()

    fun observeState(): Flowable<FeedDetailsState> {
        return stateRelay.toFlowable(BackpressureStrategy.LATEST)
            .doOnSubscribe { loadFeed() }
    }

    private fun loadFeed() {
        launch(UI) {
            val feed = getFeedUseCase.execute(feedId)
            stateRelay.onNext(FeedDetailsState(feed))
        }.attachToLifecycle()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun setState(state: FeedDetailsState) {
        stateRelay.onNext(state)
    }

}