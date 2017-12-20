package io.seekord.sebastian.domain.auth

import dagger.Reusable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
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
) : NetworkAwareUseCase<AuthParams, Single<AuthData>>(networkManager) {

    override fun execute(params: AuthParams): Single<AuthData> {
        return checkNetworkAndReturn(params)
                .flatMap { authRepository.auth(it) }
                .flatMap { authRepository.saveAuthData(it).toSingle { it } }
                .subscribeOn(Schedulers.io())
    }

}