/**
 * Extensions for Kotlin BDD Mockito
 *
 * @author NikolayKul
 */

package io.seekord.sebastian.utils.mockito

import kotlinx.coroutines.experimental.runBlocking
import org.mockito.BDDMockito
import org.mockito.BDDMockito.BDDMyOngoingStubbing

internal fun <T> given(block: () -> T): BDDMyOngoingStubbing<T> = BDDMockito.given(block())

internal fun <T> givenSuspended(block: suspend () -> T) = BDDMockito.given(runBlocking { block() })

internal infix fun <T> BDDMyOngoingStubbing<T>.willReturn(block: () -> T) = willReturn(block())

/**
 * Uses a workaround with [BDDMockito.willAnswer] to throw an Exception provided by [block]
 */
internal infix fun <T> BDDMyOngoingStubbing<T>.willThrow(block: () -> Exception) = willAnswer { throw block() }
