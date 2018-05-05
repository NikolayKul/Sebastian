package io.seekord.sebastian.utils.common

/**
 * An interface with a single function [map] that transforms a [TInput] argument into a [TOutput] value
 */
interface Mapper<in TInput, out TOutput> {
    fun map(input: TInput): TOutput
}
