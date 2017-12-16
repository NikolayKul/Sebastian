package io.seekord.sebastian.domain.auth

import io.reactivex.Completable
import io.reactivex.Single
import io.seekord.sebastian.RxSchedulerRule
import io.seekord.sebastian.data.repository.auth.AuthRepository
import io.seekord.sebastian.domain.base.NetworkException
import io.seekord.sebastian.utils.NetworkManager
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
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
        given(networkManager.checkNetworkOrThrow()).willCallRealMethod()
        given(networkManager.isNetworkAvailable()).willReturn(true)
        given(repository.auth(params)).willReturn(Single.just(data))
        given(repository.saveAuthData(data)).willReturn(Completable.complete())

        val testObserver = useCase.execute(params).test()
        schedulerRule.scheduler.triggerActions()

        testObserver.assertComplete()
        verify(repository).auth(params)
        verify(repository).saveAuthData(data)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `auth failed no network`() {
        val data = mock(AuthData::class.java)
        val params = mock(AuthParams::class.java)
        given(networkManager.checkNetworkOrThrow()).willCallRealMethod()
        given(networkManager.isNetworkAvailable()).willReturn(false)
        given(repository.auth(params)).willReturn(Single.just(data))

        val testObserver = useCase.execute(params).test()

        testObserver.assertNoErrors()
        testObserver.assertNotComplete()
        schedulerRule.scheduler.triggerActions()
        testObserver.assertError(NetworkException::class.java)
    }

    @Test
    fun `auth failed wrong credentials`() {
        TODO("not implemented")
    }

}