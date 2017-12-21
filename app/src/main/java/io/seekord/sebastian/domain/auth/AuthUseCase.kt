package io.seekord.sebastian.domain.auth

import dagger.Reusable
import io.reactivex.Completable
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
) : NetworkAwareUseCase<AuthParams, Completable>(networkManager) {

    override fun execute(params: AuthParams): Completable {
        return checkNetworkAndReturn(params)
                .flatMap { authRepository.auth(it) }
                .flatMapCompletable { authRepository.saveAuthData(it) }
                .subscribeOn(Schedulers.io())
    }

}