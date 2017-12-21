package io.seekord.sebastian.domain.auth

import dagger.Reusable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.seekord.sebastian.data.repository.auth.AuthRepository
import io.seekord.sebastian.domain.base.NoParamsUseCase
import javax.inject.Inject

/**
 * Created by nikolay
 */

@Reusable
class GetAuthTokenUseCase @Inject constructor(
        private val authRepository: AuthRepository
) : NoParamsUseCase<Single<AuthData>>() {

    override fun execute(): Single<AuthData> = authRepository.getAuthData()
            .subscribeOn(Schedulers.io())

}