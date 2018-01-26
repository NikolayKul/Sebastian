package io.seekord.sebastian.domain.auth

import io.reactivex.Single
import io.seekord.sebastian.data.repository.auth.AuthRepository
import io.seekord.sebastian.utils.mockito.given
import io.seekord.sebastian.utils.mockito.willReturn
import io.seekord.sebastian.utils.rules.RxSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit

/**
 * Created by nikolay
 */

class GetAuthTokenUseCaseTest {
    @Rule @JvmField val mockitoRule = MockitoJUnit.rule()!!
    @Rule @JvmField val schedulerRule = RxSchedulerRule()
    @Mock private lateinit var repository: AuthRepository
    private lateinit var useCase: GetAuthTokenUseCase

    @Before
    fun setUp() {
        useCase = GetAuthTokenUseCase(repository)
    }

    @Test
    fun `get token success`() {
        val data = mock(AuthData::class.java)
        given { repository.getAuthData() } willReturn { Single.just(data) }

        val testObserver = useCase.execute().test()
        schedulerRule.scheduler.triggerActions()

        testObserver.assertValue(data)
    }

    @Test
    fun `token not found`() {
        given { repository.getAuthData() } willReturn { Single.error(TokenNotFoundException()) }

        val testObserver = useCase.execute().test()
        schedulerRule.scheduler.triggerActions()

        testObserver.assertError(TokenNotFoundException::class.java)
    }

}