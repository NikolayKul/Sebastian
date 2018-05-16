package com.nikolaykul.sebastian.presentation.feed.details

import android.support.annotation.VisibleForTesting
import com.nikolaykul.sebastian.domain.rss.FeedId
import com.nikolaykul.sebastian.domain.rss.GetFeedUseCase
import com.nikolaykul.sebastian.presentation.base.BaseViewModel
import io.reactivex.Flowable
import javax.inject.Inject

class FeedDetailsViewModel @Inject constructor(
    private val feedId: FeedId,
    private val getFeedUseCase: GetFeedUseCase
) : BaseViewModel() {

    fun observeState(): Flowable<FeedDetailsState> = TODO("Not implemented yet")

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun setState(state: FeedDetailsState) {
        TODO("Not implemented yet")
    }

}