package io.seekord.sebastian.utils.network

import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

/**
 * @author NikolayKul
 */


fun <T> Call<T>.toDeferred(): Deferred<T> {
    val deferred = CompletableDeferred<T>()

    // cancel request as well
    deferred.invokeOnCompletion {
        if (deferred.isCancelled) {
            cancel()
        }
    }

    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>?, t: Throwable) {
            deferred.completeExceptionally(t)
        }

        override fun onResponse(call: Call<T>?, response: Response<T>) {
            if (response.isSuccessful) {
                deferred.complete(response.body()!!)
            } else {
                deferred.completeExceptionally(HttpException(response))
            }
        }
    })

    return deferred
}