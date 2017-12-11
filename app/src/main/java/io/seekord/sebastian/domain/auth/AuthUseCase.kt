package io.seekord.sebastian.domain.auth

import dagger.Reusable
import io.reactivex.Completable
import io.seekord.sebastian.data.repository.auth.AuthRepository
import io.seekord.sebastian.domain.auth.models.AuthCredentials
import javax.inject.Inject

/**
 * Created by nikolay
 */

@Reusable
class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {

    fun auth(authCredentials: AuthCredentials): Completable {
        return authRepository.auth(authCredentials)
                .flatMapCompletable { authRepository.saveAuthData(it) }
    }

}