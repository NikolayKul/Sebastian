package com.nikolaykul.sebastian.presentation.feed.details

import com.nikolaykul.sebastian.domain.rss.FeedId
import com.nikolaykul.sebastian.domain.rss.GetFeedUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.mockito.givenSuspended
import com.nikolaykul.sebastian.utils.mockito.willReturn
import com.nikolaykul.sebastian.utils.rules.CoroutineContextRule
import com.nikolaykul.sebastian.utils.rules.RxSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FeedDetailsViewModelTest {
    @get:Rule val coroutineContextRule = CoroutineContextRule()
    @get:Rule val rxSchedulerRule = RxSchedulerRule()
    @Mock private lateinit var feedId: FeedId
    @Mock private lateinit var getFeedUseCase: GetFeedUseCase
    private lateinit var viewModel: FeedDetailsViewModel

    @Before
    fun setUp() {
        viewModel = FeedDetailsViewModel(feedId, getFeedUseCase)
    }

    @Test
    fun `loads initial state`() {
        val givenFeed = stubFeed()
        givenSuspended { getFeedUseCase.execute(feedId) } willReturn { givenFeed }

        val actualStateRelay = viewModel.observeState().test()

        val expectedState = FeedDetailsState(feed = givenFeed)
        actualStateRelay.assertValue(expectedState)
        actualStateRelay.assertNotTerminated()
    }

    @Test
    fun `returns last state`() {
        val expectedState = FeedDetailsState(feed = null)
        viewModel.setState(expectedState)

        val actualStateRelay = viewModel.observeState().test()

        actualStateRelay.assertValue(expectedState)
        actualStateRelay.assertNotTerminated()
    }

    private fun stubFeed() = RssFeed("", "", "", null)

}