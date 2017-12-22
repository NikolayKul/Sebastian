package io.seekord.sebastian.domain.base

import io.seekord.sebastian.utils.NetworkManager

/**
 * Created by nikolay
 */

interface UseCase<in T : Any?, out R : Any> {
    suspend fun execute(params: T): R
}

abstract class NoParamsUseCase<out R : Any> : UseCase<Any?, R> {
    @Deprecated("Use a function without params", ReplaceWith("execute()"))
    override suspend fun execute(params: Any?) = execute()

    abstract suspend fun execute(): R
}

abstract class NetworkAwareUseCase<in T : Any?, out R : Any>(
        private val networkManager: NetworkManager
) : UseCase<T, R> {

    protected fun checkNetworkOrThrow() {
        if (!networkManager.isNetworkAvailable()) {
            throw NetworkException()
        }
    }

}