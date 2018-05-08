package com.nikolaykul.sebastian.presentation.main

import com.nikolaykul.sebastian.domain.NetworkException
import com.nikolaykul.sebastian.domain.rss.GetChannelUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.mockito.givenSuspended
import com.nikolaykul.sebastian.utils.mockito.willReturn
import com.nikolaykul.sebastian.utils.mockito.willThrow
import com.nikolaykul.sebastian.utils.rules.CoroutineContextRule
import com.nikolaykul.sebastian.utils.rules.JodaTimeRule
import kotlinx.coroutines.experimental.runBlocking
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {
    @get:Rule val jodaTimeRule = JodaTimeRule()
    @get:Rule val coroutineContextRule = CoroutineContextRule()
    @Mock private lateinit var getRssChannelUseCase: GetChannelUseCase
    @Mock private lateinit var view: MainMvpView
    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        presenter = MainPresenterImpl(getRssChannelUseCase)
                .apply { attachView(view) }
    }

    @Test
    fun `load rss previews`() = runBlocking {
        val feeds = (0..10).map {
            RssFeed("id_$it", "title_$it", "subtitle_$it", DateTime())
        }
        val channel = RssChannel("", "", "", "", feeds)
        givenSuspended { getRssChannelUseCase.execute() } willReturn { channel }

        presenter.loadFeeds()

        verify(view).showFeeds(feeds)
    }

    @Test
    fun `load rss previews no internet exception`() = runBlocking {
        val exception = NetworkException()
        givenSuspended { getRssChannelUseCase.execute() } willThrow { exception }

        presenter.loadFeeds()

        verify(view).showError(exception)
    }

}