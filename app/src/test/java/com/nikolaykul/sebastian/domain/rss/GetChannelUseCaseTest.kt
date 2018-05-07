package com.nikolaykul.sebastian.domain.rss

import com.nikolaykul.sebastian.data.repository.rss.RssRepository
import com.nikolaykul.sebastian.domain.rss.models.RssChannel
import com.nikolaykul.sebastian.utils.mockito.givenSuspended
import com.nikolaykul.sebastian.utils.mockito.willReturn
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
    fun `load channel`() = runBlocking {
        val givenChannel = RssChannel("title", "link", "description", emptyList())
        givenSuspended { repository.getRssChannel() } willReturn { givenChannel }

        val resultChannel = useCase.execute()

        assertThat(resultChannel, `is`(givenChannel))
    }

    @Test
    fun `throw no internet exception`() {
        TODO("Create NetworkManager")
    }

}