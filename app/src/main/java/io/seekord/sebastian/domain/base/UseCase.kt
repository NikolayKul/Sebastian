package io.seekord.sebastian.domain.base

/**
 * Created by nikolay
 */

interface UseCase<in T : Any?, out R : Any> {
    fun execute(params: T): R
}

abstract class NoParamsUseCase<out R : Any> : UseCase<Any?, R> {
    @Deprecated("Use a function without params", ReplaceWith("execute()"))
    override fun execute(params: Any?) = execute()

    abstract fun execute(): R
}