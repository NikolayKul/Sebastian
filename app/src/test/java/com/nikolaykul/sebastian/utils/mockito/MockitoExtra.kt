/**
 * Mockito extensions for Kotlin
 */

package com.nikolaykul.sebastian.utils.mockito

import kotlinx.coroutines.experimental.runBlocking
import org.mockito.BDDMockito

internal fun <T> given(block: () -> T): BDDMockito.BDDMyOngoingStubbing<T> =
        BDDMockito.given(block())

internal fun <T> givenSuspended(block: suspend () -> T) =
        BDDMockito.given(runBlocking { block() })

internal infix fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willReturn(block: () -> T) =
        willReturn(block())

/**
 * Uses a workaround with [BDDMockito.willAnswer] to throw an Exception provided by [block]
 */
internal infix fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willThrow(block: () -> Exception) =
        willAnswer { throw block() }
