package io.seekord.sebastian.domain.auth

import dagger.Reusable
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import io.seekord.sebastian.data.repository.auth.AuthRepository
import io.seekord.sebastian.domain.auth.models.AuthCredentials
import io.seekord.sebastian.domain.base.NetworkAwareUseCase
import javax.inject.Inject

/**
 * Created by nikolay
 */

@Reusable
class AuthUseCase @Inject constructor(private val authRepository: AuthRepository)
    : NetworkAwareUseCase<AuthCredentials, Completable>() {

    override fun execute(params: AuthCredentials): Completable {
        return checkNetwork()
                .andThen(authRepository.auth(params))
                .flatMapCompletable { authRepository.saveAuthData(it) }
                .subscribeOn(Schedulers.io())
    }

}