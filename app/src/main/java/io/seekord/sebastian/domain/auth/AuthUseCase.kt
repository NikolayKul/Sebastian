package io.seekord.sebastian.domain.auth

import dagger.Reusable
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import io.seekord.sebastian.data.repository.auth.AuthRepository
import io.seekord.sebastian.domain.base.UseCase
import io.seekord.sebastian.utils.NetworkManager
import javax.inject.Inject

/**
 * Created by nikolay
 */

@Reusable
class AuthUseCase @Inject constructor(
        private val networkManager: NetworkManager,
        private val authRepository: AuthRepository)
    : UseCase<AuthParams, Completable> {

    override fun execute(params: AuthParams): Completable {
        return networkManager.checkNetworkOrThrow()
                .andThen(authRepository.auth(params))
                .flatMapCompletable { authRepository.saveAuthData(it) }
                .subscribeOn(Schedulers.io())
    }

}