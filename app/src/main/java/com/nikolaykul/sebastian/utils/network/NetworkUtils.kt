package com.nikolaykul.sebastian.utils.network

import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> Call<T>.await(): T = suspendCancellableCoroutine { cont ->
    cont.invokeOnCancellation { cancel() }
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            cont.resumeWith(Result.failure(t))
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            cont.resumeWith(
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(HttpException(response))
                }
            )
        }
    })
}
