package io.seekord.sebastian.domain.auth

import com.nhaarman.mockito_kotlin.*
import io.seekord.sebastian.CoroutineContextRule
import io.seekord.sebastian.data.repository.auth.AuthRepository
import io.seekord.sebastian.utils.NetworkManager
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

/**
 * Created by nikolay
 */

internal class AuthUseCaseTest {
    @Rule @JvmField val mockitoRule = MockitoJUnit.rule()!!
    @Rule @JvmField val schedulerRule = CoroutineContextRule()
    @Mock private lateinit var networkManager: NetworkManager
    @Mock private lateinit var repository: AuthRepository
    private lateinit var useCase: AuthUseCase

    @Before
    internal fun setUp() {
        useCase = AuthUseCase(networkManager, repository)
    }

    @Test
    internal fun `auth success`() {
        val authData = mock<AuthData>()
        given { networkManager.isNetworkAvailable() } willReturn { true }
        given { runBlocking { repository.auth(any()) } } willReturn { authData }
        given { runBlocking { repository.saveAuthData(any()) } } willReturn { Unit }

        runBlocking {
            val authParams = mock<AuthParams>()
            useCase.execute(authParams)

            then(repository).should().auth(authParams)
            then(repository).should().saveAuthData(authData)
        }
    }

//    private fun <T : Any, R> KStubbing<T>.mk(m: suspend T.() -> R): OngoingStubbing<R> {
//        return runBlocking { `when`(m()) }
//    }
//
//    @Test
//    internal fun `auth failed no network`() {
//        val params = mock(AuthParams::class.java)
//        given(networkManager.isNetworkAvailable()).willReturn(false)
//
//        val testObserver = useCase.execute(params).test()
//
//        schedulerRule.scheduler.triggerActions()
//        testObserver.assertError(NetworkException::class.java)
//    }
//
//    @Test
//    internal fun `auth failed wrong credentials`() {
//        val params = mock(AuthParams::class.java)
//        given(networkManager.isNetworkAvailable()).willReturn(true)
//        given(repository.auth(params)).willReturn(Single.error(AccountNotFoundException()))
//
//        val testObserver = useCase.execute(params).test()
//
//        schedulerRule.scheduler.triggerActions()
//        testObserver.assertError(AccountNotFoundException::class.java)
//    }

}