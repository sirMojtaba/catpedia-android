package com.example.data.remote.util

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithApiKey = originalRequest.newBuilder()
            .addHeader("x-api-key", NetworkConstants.API_KEY)
            .addHeader("content-type", NetworkConstants.CONTENT_TYPE)
            .build()
        return chain.proceed(requestWithApiKey)
    }
}