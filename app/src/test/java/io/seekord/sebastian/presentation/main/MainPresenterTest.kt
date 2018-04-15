package io.seekord.sebastian.presentation.main

import io.seekord.sebastian.domain.rss.GetRssItemsUseCase
import io.seekord.sebastian.domain.rss.models.RssItem
import io.seekord.sebastian.utils.coroutine.CoroutineContextProvider
import io.seekord.sebastian.utils.mockito.givenSuspended
import io.seekord.sebastian.utils.mockito.willReturn
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author NikolayKul
 */
@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {
    @Mock private lateinit var itemsUseCase: GetRssItemsUseCase
    @Mock private lateinit var view: MainMvpView
    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        presenter = MainPresenter(itemsUseCase)
                .apply { attachView(view) }
        CoroutineContextProvider.mockContexts()
    }

    @Test
    fun `load rss previews`() {
        val items = listOf<RssItem>()
        givenSuspended { itemsUseCase.getRssItems() } willReturn { items }

        runBlocking { presenter.loadRssPreviews() }

        verify(view).showRssPreviews(items)
        verifyNoMoreInteractions(view)
    }

}