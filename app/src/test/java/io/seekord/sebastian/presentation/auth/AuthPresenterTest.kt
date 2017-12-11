package io.seekord.sebastian.presentation.auth

import io.seekord.sebastian.data.repository.auth.AuthRepository
import io.seekord.sebastian.domain.auth.AuthUseCase
import org.junit.Before
import org.junit.Test

/**
 * Created by nikolay
 */

class AuthPresenterTest {
    private lateinit var authPresenter: AuthPresenter

    @Before
    fun setUp() {
        val useCase = AuthUseCase(AuthRepository())
        authPresenter = AuthPresenter(useCase)
    }

    @Test
    fun `auth success`() {
        TODO("Not implemented yet")
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