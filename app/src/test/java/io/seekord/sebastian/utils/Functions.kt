package io.seekord.sebastian.utils

import kotlinx.coroutines.experimental.runBlocking
import org.mockito.BDDMockito.given

/**
 * Created by nikolay
 */

internal fun <T> givenSuspended(methodCall: suspend () -> T) = given(runBlocking { methodCall() })
