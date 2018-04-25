package io.seekord.sebastian.presentation.main

import io.seekord.sebastian.domain.NetworkException
import io.seekord.sebastian.domain.rss.GetRssItemsUseCase
import io.seekord.sebastian.domain.rss.models.RssItem
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

/**
 * @author NikolayKul
 */
@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {
    @get:Rule val jodaTimeRule = JodaTimeRule()
    @get:Rule val coroutineContextRule = CoroutineContextRule()
    @Mock private lateinit var itemsUseCase: GetRssItemsUseCase
    @Mock private lateinit var view: MainMvpView
    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        presenter = MainPresenterImpl(itemsUseCase)
                .apply { attachView(view) }
    }

    @Test
    fun `load rss previews`() = runBlocking {
        val items = (0..10).map {
            RssItem("title_$it", "subtitle_$it", DateTime())
        }
        givenSuspended { itemsUseCase.execute() } willReturn { items }

        presenter.loadRssPreviews()

        verify(view).showRssPreviews(items)
    }

    @Test
    fun `load rss previews no internet exception`() = runBlocking {
        val exception = NetworkException()
        givenSuspended { itemsUseCase.execute() } willThrow { exception }

        presenter.loadRssPreviews()

        verify(view).showError(exception)
    }

}