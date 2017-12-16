package io.seekord.sebastian.domain.base

/**
 * Created by nikolay
 */

interface UseCase<in T : Any?, out R : Any> {
    fun execute(params: T): R
}