package io.seekord.sebastian.utils.mockito

import kotlinx.coroutines.experimental.runBlocking
import org.mockito.BDDMockito
import org.mockito.BDDMockito.BDDMyOngoingStubbing

/**
 * Created by nikolay
 */

fun <T> given(method: () -> T): BDDMyOngoingStubbing<T> = BDDMockito.given(method())

infix fun <T> BDDMyOngoingStubbing<T>.willReturn(method: () -> T): BDDMyOngoingStubbing<T> = willReturn(method())

internal fun <T> givenSuspended(methodCall: suspend () -> T) = BDDMockito.given(runBlocking { methodCall() })
