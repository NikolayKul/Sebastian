package io.seekord.sebastian.data.api

import io.seekord.sebastian.domain.auth.AuthData
import io.seekord.sebastian.domain.auth.AuthParams
import java.lang.Thread.sleep
import java.lang.reflect.Proxy
import java.util.*
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
            sleep(STUB_API_DELAY)
            method.invoke(StubApi, *args)
        } as SebastianApi
    }

}

private object StubApi : SebastianApi {

    override fun auth(credentials: AuthParams) = AuthData(UUID.randomUUID().toString())

}