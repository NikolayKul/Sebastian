package io.seekord.sebastian.domain.auth

import dagger.Reusable
import io.seekord.sebastian.data.repository.auth.AuthRepository
import io.seekord.sebastian.domain.base.NetworkAwareUseCase
import io.seekord.sebastian.utils.NetworkManager
import javax.inject.Inject

/**
 * Created by nikolay
 */

@Reusable
class AuthUseCase @Inject constructor(
        networkManager: NetworkManager,
        private val authRepository: AuthRepository
) : NetworkAwareUseCase<AuthParams, Unit>(networkManager) {

    override suspend fun execute(params: AuthParams) {
        checkNetworkOrThrow()
        val authData = authRepository.auth(params)
        authRepository.saveAuthData(authData)
    }

}