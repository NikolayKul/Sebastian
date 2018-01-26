package io.seekord.sebastian.utils.mockito

import org.mockito.BDDMockito
import org.mockito.BDDMockito.BDDMyOngoingStubbing

/**
 * Created by nikolay
 */

fun <T> given(method: () -> T): BDDMyOngoingStubbing<T> = BDDMockito.given(method())

infix fun <T> BDDMyOngoingStubbing<T>.willReturn(method: () -> T): BDDMyOngoingStubbing<T> = willReturn(method())