package com.nikolaykul.sebastian.utils.network

import kotlinx.coroutines.experimental.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

/**
 * @author NikolayKul
 */


suspend fun <T> Call<T>.await(): T = suspendCancellableCoroutine { cont ->
    cont.invokeOnCompletion { cancel() }
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            cont.resumeWithException(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                cont.resume(response.body()!!)
            } else {
                cont.resumeWithException(HttpException(response))
            }
        }
    })
}
