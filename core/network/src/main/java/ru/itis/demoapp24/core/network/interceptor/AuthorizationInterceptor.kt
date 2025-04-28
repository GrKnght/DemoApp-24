package ru.itis.demoapp24.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import ru.itis.demoapp24.core.network.BuildConfig as networkConfig

class AuthorizationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
            .addHeader("Authorization", "Bearer ${networkConfig.API_KEY}")

        return chain.proceed(builder.build())
    }
}