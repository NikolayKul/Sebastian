package com.nikolaykul.sebastian.domain.rss

import com.nikolaykul.sebastian.data.repository.rss.RssRepository
import com.nikolaykul.sebastian.domain.NoNetworkException
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import com.nikolaykul.sebastian.utils.mockito.givenSuspended
import com.nikolaykul.sebastian.utils.mockito.willReturn
import com.nikolaykul.sebastian.utils.mockito.willThrow
import kotlinx.coroutines.experimental.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetChannelUseCaseTest {
    @Mock private lateinit var repository: RssRepository
    private lateinit var useCase: GetChannelUseCase

    @Before
    fun setUp() {
        useCase = GetChannelUseCase(repository)
    }

    @Test
    fun `loads channel from repository`() = runBlocking {
        val givenChannel = RssChannel("id", "title", "link", "description", emptyList())
        givenSuspended { repository.getRssChannel() } willReturn { givenChannel }

        val resultChannel = useCase.execute()

        assertThat(resultChannel, `is`(givenChannel))
    }

    @Test(expected = NoNetworkException::class)
    fun `propagates no network exception`() = runBlocking {
        val exception = NoNetworkException()
        givenSuspended { repository.getRssChannel() } willThrow { exception }

        useCase.execute()

        return@runBlocking
    }

}