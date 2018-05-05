package io.seekord.sebastian.presentation.main

import io.seekord.sebastian.domain.NetworkException
import io.seekord.sebastian.domain.rss.GetChannelUseCase
import io.seekord.sebastian.domain.rss.models.RssChannel
import io.seekord.sebastian.domain.rss.models.RssFeed
import io.seekord.sebastian.utils.mockito.givenSuspended
import io.seekord.sebastian.utils.mockito.willReturn
import io.seekord.sebastian.utils.mockito.willThrow
import io.seekord.sebastian.utils.rules.CoroutineContextRule
import io.seekord.sebastian.utils.rules.JodaTimeRule
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
        val channel = RssChannel("", "", "", feeds)
        givenSuspended { getRssChannelUseCase.execute() } willReturn { channel }

        presenter.loadRssPreviews()

        verify(view).showRssPreviews(feeds)
    }

    @Test
    fun `load rss previews no internet exception`() = runBlocking {
        val exception = NetworkException()
        givenSuspended { getRssChannelUseCase.execute() } willThrow { exception }

        presenter.loadRssPreviews()

        verify(view).showError(exception)
    }

}