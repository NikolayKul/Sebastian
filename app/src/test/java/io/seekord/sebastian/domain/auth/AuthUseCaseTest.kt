package io.seekord.sebastian.domain.auth

import io.reactivex.Completable
import io.reactivex.Single
import io.seekord.sebastian.data.repository.auth.AuthRepository
import io.seekord.sebastian.domain.base.NetworkException
import io.seekord.sebastian.utils.NetworkManager
import io.seekord.sebastian.utils.mockito.given
import io.seekord.sebastian.utils.mockito.willReturn
import io.seekord.sebastian.utils.rules.RxSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

/**
 * Created by nikolay
 */

class AuthUseCaseTest {
    @Rule @JvmField val mockitoRule = MockitoJUnit.rule()!!
    @Rule @JvmField val schedulerRule = RxSchedulerRule()
    @Mock private lateinit var networkManager: NetworkManager
    @Mock private lateinit var repository: AuthRepository
    private lateinit var useCase: AuthUseCase

    @Before
    fun setUp() {
        useCase = AuthUseCase(networkManager, repository)
    }

    @Test
    fun `auth success`() {
        val data = mock(AuthData::class.java)
        val params = mock(AuthParams::class.java)
        given { networkManager.checkNetworkOrThrow() } willReturn { Completable.complete() }
        given { repository.auth(params) } willReturn { Single.just(data) }
        given { repository.saveAuthData(data) } willReturn { Completable.complete() }

        val testObserver = useCase.execute(params).test()
        schedulerRule.scheduler.triggerActions()

        testObserver.assertComplete()
        verify(repository).auth(params)
        verify(repository).saveAuthData(data)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `auth failed no network`() {
        val params = mock(AuthParams::class.java)
        given { networkManager.checkNetworkOrThrow() } willReturn { Completable.error(NetworkException()) }

        val testObserver = useCase.execute(params).test()
        schedulerRule.scheduler.triggerActions()

        testObserver.assertError(NetworkException::class.java)
    }

    @Test
    fun `auth failed wrong credentials`() {
        val params = mock(AuthParams::class.java)
        given { networkManager.checkNetworkOrThrow() } willReturn { Completable.complete() }
        given { repository.auth(params) } willReturn { Single.error(AccountNotFoundException()) }

        val testObserver = useCase.execute(params).test()
        schedulerRule.scheduler.triggerActions()

        testObserver.assertError(AccountNotFoundException::class.java)
    }

}