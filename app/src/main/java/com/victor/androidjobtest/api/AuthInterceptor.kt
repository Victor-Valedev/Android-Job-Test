package com.victor.androidjobtest.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()

        val request = requestBuilder.addHeader(
            "Authorization",
            "Client-ID ${RetrofitService.TOKEN_CLIENT_ID}"
        ).build()


        return chain.proceed(request)
    }

}