package com.nikolaykul.sebastian.data.repository.rss

import com.nikolaykul.sebastian.data.store.rss.local.RssLocalStore
import com.nikolaykul.sebastian.data.store.rss.remote.RssRemoteStore
import com.nikolaykul.sebastian.domain.NoNetworkException
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import com.nikolaykul.sebastian.utils.common.NetworkManager
import com.nikolaykul.sebastian.utils.mockito.given
import com.nikolaykul.sebastian.utils.mockito.givenSuspended
import com.nikolaykul.sebastian.utils.mockito.mock
import com.nikolaykul.sebastian.utils.mockito.willReturn
import com.nikolaykul.sebastian.utils.rules.CoroutineContextRule
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RssRepositoryTest {
    @get:Rule val coroutineContextRule = CoroutineContextRule()
    @Mock private lateinit var networkManager: NetworkManager
    @Mock private lateinit var remoteStore: RssRemoteStore
    @Mock private lateinit var localStore: RssLocalStore
    private lateinit var repository: RssRepository

    @Before
    fun setUp() {
        repository = RssRepository(networkManager, remoteStore, localStore)
    }

    @Test
    fun `fetches channel from local`() = runBlocking {
        val givenChannels = listOf(mock<RssChannel>())
        givenSuspended { localStore.getAllChannels() } willReturn { givenChannels }

        val resultChannel = repository.getRssChannel()

        assertThat(resultChannel, `is`(givenChannels[0]))
        verify(localStore).getAllChannels()
        verifyNoMoreInteractions(remoteStore)
    }

    @Test
    fun `fetches channel from remote`() = runBlocking {
        val givenChannel = mock<RssChannel>()
        givenSuspended { localStore.getAllChannels() } willReturn { emptyList() }
        given { networkManager.isNetworkAvailable() } willReturn { true }
        givenSuspended { remoteStore.fetchChannel() } willReturn { givenChannel }

        val resultChannel = repository.getRssChannel()

        assertThat(resultChannel, `is`(givenChannel))
        verify(localStore).getAllChannels()
        verify(remoteStore).fetchChannel()

        return@runBlocking
    }

    @Test
    fun `saves repository from network`() = runBlocking {
        val givenChannel = mock<RssChannel>()
        givenSuspended { localStore.getAllChannels() } willReturn { emptyList() }
        given { networkManager.isNetworkAvailable() } willReturn { true }
        givenSuspended { remoteStore.fetchChannel() } willReturn { givenChannel }

        repository.getRssChannel()

        verify(remoteStore).fetchChannel()
        verify(localStore).saveChannel(givenChannel)
    }

    @Test(expected = NoNetworkException::class)
    fun `throws no network exception`() = runBlocking {
        givenSuspended { localStore.getAllChannels() } willReturn { emptyList() }
        given { networkManager.isNetworkAvailable() } willReturn { false }

        repository.getRssChannel()

        return@runBlocking
    }

}