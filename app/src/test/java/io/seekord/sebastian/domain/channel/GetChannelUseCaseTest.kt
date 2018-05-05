package io.seekord.sebastian.domain.channel

import io.seekord.sebastian.data.repository.RssRepository
import io.seekord.sebastian.domain.channel.models.RssChannel
import io.seekord.sebastian.utils.mockito.givenSuspended
import io.seekord.sebastian.utils.mockito.willReturn
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