package io.seekord.sebastian.domain.base

import io.reactivex.Completable

/**
 * Created by nikolay
 */

abstract class UseCase<in T : Any?, out R : Any> {
    abstract fun execute(params: T): R
}

abstract class NetworkAwareUseCase<in T : Any?, out R : Any> : UseCase<T, R>() {

    protected fun checkNetwork(): Completable = Completable.defer {
        return@defer if (isNetworkAvailable()) {
            Completable.complete()
        } else {
            Completable.error(NetworkException())
        }
    }

    private fun isNetworkAvailable() = true

}