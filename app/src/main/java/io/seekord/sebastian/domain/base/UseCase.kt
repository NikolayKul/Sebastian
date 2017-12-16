package io.seekord.sebastian.domain.base

import io.reactivex.Single
import io.seekord.sebastian.utils.NetworkManager

/**
 * Created by nikolay
 */

interface UseCase<in T : Any?, out R : Any> {
    fun execute(params: T): R
}

abstract class NetworkAwareUseCase<T : Any?, out R : Any>(
        private val networkManager: NetworkManager
) : UseCase<T, R> {

    protected fun checkNetworkAndReturn(params: T): Single<T> = Single.defer {
        if (networkManager.isNetworkAvailable()) {
            Single.just(params)
        } else {
            Single.error(NetworkException())
        }
    }

}