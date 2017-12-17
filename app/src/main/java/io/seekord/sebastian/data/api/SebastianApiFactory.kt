package io.seekord.sebastian.data.api

import io.reactivex.Completable
import io.reactivex.Single
import io.seekord.sebastian.domain.auth.AuthData
import io.seekord.sebastian.domain.auth.AuthParams
import java.lang.reflect.Proxy
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by nikolay
 */

private const val STUB_API_DELAY = 2_000L

@Singleton
class SebastianApiFactory @Inject constructor() {

    fun createStubApiWithDelay(): SebastianApi {
        val clazz = SebastianApi::class.java
        return Proxy.newProxyInstance(clazz.classLoader, arrayOf(clazz)) { _, method, args ->
            val methodResult = method.invoke(StubApi, *args)
            when (methodResult) {
            // duplicate because non of them share the same interface
                is Single<*> -> methodResult.delay(STUB_API_DELAY, TimeUnit.MILLISECONDS)
                is Completable -> methodResult.delay(STUB_API_DELAY, TimeUnit.MILLISECONDS)
                else -> methodResult
            }
        } as SebastianApi
    }

}

private object StubApi : SebastianApi {

    override fun auth(credentials: AuthParams): Single<AuthData> = Single.fromCallable {
        val accessToken = UUID.randomUUID().toString()
        AuthData(accessToken)
    }

}