package com.nikolaykul.sebastian.presentation.main

import com.nikolaykul.sebastian.domain.NoNetworkException
import com.nikolaykul.sebastian.domain.rss.GetChannelUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
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
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule val coroutineContextRule = CoroutineContextRule()
    @get:Rule val rxSchedulerRule = RxSchedulerRule()
    @Mock private lateinit var getRssChannelUseCase: GetChannelUseCase
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(getRssChannelUseCase)
    }

    @Test
    fun `emits feeds from clear state`() = runBlocking {
        val givenFeeds = stubFeeds()
        givenSuspended { getRssChannelUseCase.execute() } willReturn { stubChannel(givenFeeds) }

        val expectedStates = arrayOf(
                MainState(),
                MainState(isLoading = true),
                MainState(feeds = givenFeeds))

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
                MainState(error = givenError),
                MainState(isLoading = true, error = givenError),
                MainState(feeds = givenFeeds))

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
                MainState(),
                MainState(isLoading = true),
                MainState(error = givenException))

        val actualStateRelay = observeFromGivenState(expectedStates[0])
        viewModel.loadChannel()

        actualStateRelay.assertValues(*expectedStates)

        return@runBlocking
    }

    /**
     * Set initial [MainState] and return a [TestSubscriber] to the State relay
     */
    private fun observeFromGivenState(state: MainState) = with(viewModel) {
        setState(state)
        return@with observeState().test()
    }

    private fun stubChannel(feeds: List<RssFeed>) = RssChannel("", "", "", "", feeds)

    private fun stubFeeds() = (1..5).map { RssFeed("$it", "title_$it", "desc_$it", null) }

}