package com.nikolaykul.sebastian.presentation.main

import com.nikolaykul.sebastian.domain.NoNetworkException
import com.nikolaykul.sebastian.domain.rss.GetChannelUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.mockito.givenSuspended
import com.nikolaykul.sebastian.utils.mockito.mock
import com.nikolaykul.sebastian.utils.mockito.willReturn
import com.nikolaykul.sebastian.utils.mockito.willThrow
import com.nikolaykul.sebastian.utils.rules.CoroutineContextRule
import com.nikolaykul.sebastian.utils.rules.JodaTimeRule
import com.nikolaykul.sebastian.utils.rules.RxSchedulerRule
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule val jodaTimeRule = JodaTimeRule()
    @get:Rule val coroutineContextRule = CoroutineContextRule()
    @get:Rule val rxSchedulerRule = RxSchedulerRule()
    @Mock private lateinit var getRssChannelUseCase: GetChannelUseCase
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(getRssChannelUseCase)
    }

    @Test
    fun `emit feeds`() = runBlocking {
        val expectedFeeds = mock<List<RssFeed>>()
        val expectedChannel = RssChannel("", "", "", "", expectedFeeds)
        givenSuspended { getRssChannelUseCase.execute() } willReturn { expectedChannel }

        val feedsFlowable = viewModel.observeFeeds().test()
        feedsFlowable.assertNoValues()

        viewModel.loadChannel()

        feedsFlowable.assertValue(expectedFeeds)

        return@runBlocking
    }

    @Test
    fun `toggles loading on load complete`() = runBlocking {
        givenSuspended { getRssChannelUseCase.execute() } willReturn { mock() }

        val loadingFlowable = viewModel.observeLoading().test()
        loadingFlowable.assertNoValues()

        viewModel.loadChannel()

        loadingFlowable.assertValues(true, false)

        return@runBlocking
    }

    @Test
    fun `toggles loading on load error`() = runBlocking {
        givenSuspended { getRssChannelUseCase.execute() } willThrow { NoNetworkException() }

        val loadingFlowable = viewModel.observeLoading().test()
        loadingFlowable.assertNoValues()

        viewModel.loadChannel()

        loadingFlowable.assertValues(true, false)

        return@runBlocking
    }

}