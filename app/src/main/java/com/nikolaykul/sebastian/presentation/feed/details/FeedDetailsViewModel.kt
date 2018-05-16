package com.nikolaykul.sebastian.presentation.feed.details

import com.nikolaykul.sebastian.domain.rss.FeedId
import com.nikolaykul.sebastian.domain.rss.GetFeedUseCase
import com.nikolaykul.sebastian.presentation.base.BaseViewModel
import io.reactivex.Flowable
import javax.inject.Inject

class FeedDetailsViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase
) : BaseViewModel() {

    fun observeState(feedId: FeedId): Flowable<FeedId> = TODO("Not implemented yet")

}