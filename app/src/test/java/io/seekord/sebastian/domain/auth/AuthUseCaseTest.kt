package io.seekord.sebastian.domain.auth

import io.reactivex.Completable
import io.reactivex.Single
import io.seekord.sebastian.RxSchedulerRule
import io.seekord.sebastian.data.repository.auth.AuthRepository
import io.seekord.sebastian.domain.auth.models.AuthData
import io.seekord.sebastian.domain.auth.models.AuthParams
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
        val authData = mock(AuthData::class.java)
        val authCredentials = mock(AuthParams::class.java)
        given(networkManager.checkNetworkOrThrow()).willReturn(Completable.complete())
        given(repository.auth(authCredentials)).willReturn(Single.just(authData))
        given(repository.saveAuthData(authData)).willReturn(Completable.complete())

        val testObserver = useCase.execute(authCredentials).test()
        schedulerRule.scheduler.triggerActions()

        testObserver.assertComplete()
        verify(repository).auth(authCredentials)
        verify(repository).saveAuthData(authData)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `auth failed no connection`() {
        TODO("not implemented")
    }

    @Test
    fun `auth failed wrong credentials`() {
        TODO("not implemented")
    }
}