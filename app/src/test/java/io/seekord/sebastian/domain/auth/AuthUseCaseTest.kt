package io.seekord.sebastian.domain.auth

import io.seekord.sebastian.data.repository.auth.AuthRepository
import org.junit.Before
import org.junit.Test

/**
 * Created by nikolay
 */

class AuthUseCaseTest {
    private lateinit var useCase: AuthUseCase

    @Before
    fun setUp() {
        val authRepository = AuthRepository()
        useCase = AuthUseCase(authRepository)
    }

    @Test
    fun `auth success`() {
        TODO("not implemented")
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