package io.seekord.sebastian.domain

/**
 * Interface with a single suspending function that accepts a generic argument and returns a generic result
 *
 * @see UseCaseNoParams
 */
interface UseCase<in TParams : Any, out TResult : Any> {
    suspend fun execute(params: TParams): TResult

}

/**
 * Interface with a single suspending function and returns a generic result
 *
 * @see UseCase
 */
interface UseCaseNoParams<out TResult : Any> {
    suspend fun execute(): TResult
}
