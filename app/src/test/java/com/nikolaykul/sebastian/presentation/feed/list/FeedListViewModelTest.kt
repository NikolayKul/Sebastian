package com.nikolaykul.sebastian.presentation.feed.list

import com.nikolaykul.sebastian.domain.NoNetworkException
import com.nikolaykul.sebastian.domain.rss.GetChannelUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.SCREEN_FEED_DETAILS
import com.nikolaykul.sebastian.utils.mockito.givenSuspended
import com.nikolaykul.sebastian.utils.mockito.willReturn
import com.nikolaykul.sebastian.utils.mockito.willThrow
import com.nikolaykul.sebastian.utils.rules.CoroutineContextRule
import com.nikolaykul.sebastian.utils.rules.RxSchedulerRule
import io.reactivex.subscribers.TestSubscriber
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import ru.terrakok.cicerone.Router

@RunWith(MockitoJUnitRunner::class)
class FeedListViewModelTest {
    @get:Rule val coroutineContextRule = CoroutineContextRule()
    @get:Rule val rxSchedulerRule = RxSchedulerRule()
    @Mock private lateinit var getRssChannelUseCase: GetChannelUseCase
    @Mock private lateinit var router: Router
    private lateinit var viewModel: FeedListViewModel

    @Before
    fun setUp() {
        viewModel = FeedListViewModel(getRssChannelUseCase, router)
    }

    @Test
    fun `emits feeds from clear state`() = runBlocking {
        val givenFeeds = stubFeeds()
        givenSuspended { getRssChannelUseCase.execute() } willReturn { stubChannel(givenFeeds) }

        val expectedStates = arrayOf(
                FeedListState(),
                FeedListState(isLoading = true),
                FeedListState(feeds = givenFeeds))

        val actualStateRelay = observeFromGivenState(expectedStates[0])
        viewModel.loadChannel()

        actualStateRelay.assertValues(*expectedStates)

        return@runBlocking
    }

    @Test
    fun `emits feeds from error state`() = runBlocking {
        val givenFeeds = stubFeeds()
        givenSuspended { getRssChannelUseCase.execute() } willReturn { stubChannel(givenFeeds) }

        val givenError = NoNetworkException()
        val expectedStates = arrayOf(
                FeedListState(error = givenError),
                FeedListState(isLoading = true, error = givenError),
                FeedListState(feeds = givenFeeds))

        val actualStateRelay = observeFromGivenState(expectedStates[0])
        viewModel.loadChannel()

        actualStateRelay.assertValues(*expectedStates)

        return@runBlocking
    }

    @Test
    fun `emits error state`() = runBlocking {
        val givenException = NoNetworkException()
        givenSuspended { getRssChannelUseCase.execute() } willThrow { givenException }

        val expectedStates = arrayOf(
                FeedListState(),
                FeedListState(isLoading = true),
                FeedListState(error = givenException))

        val actualStateRelay = observeFromGivenState(expectedStates[0])
        viewModel.loadChannel()

        actualStateRelay.assertValues(*expectedStates)

        return@runBlocking
    }

    @Test
    fun `navigates to the details screen on feed click`() {
        val expectedId = "id"
        val givenFeed = RssFeed(expectedId, "", "", null)

        viewModel.onFeedClicked(givenFeed)

        verify(router).navigateTo(SCREEN_FEED_DETAILS, expectedId)
    }

    /**
     * Set initial [FeedListState] and return a [TestSubscriber] to the State relay
     */
    private fun observeFromGivenState(state: FeedListState) = with(viewModel) {
        setState(state)
        return@with observeState().test()
    }

    private fun stubChannel(feeds: List<RssFeed>) = RssChannel("", "", "", "", feeds)

    private fun stubFeeds() = (1..5).map { RssFeed("$it", "title_$it", "desc_$it", null) }

}