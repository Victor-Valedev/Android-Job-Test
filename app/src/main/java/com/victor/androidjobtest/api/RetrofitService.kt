package com.victor.androidjobtest.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    const val BASE_URL = "https://api.imgur.com/3/"
    const val TOKEN_CLIENT_ID = ""

    val imgurAPI by lazy {
        Retrofit.Builder()
            .apply {
                baseUrl(BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
                client(
                    OkHttpClient.Builder()
                        .addInterceptor(AuthInterceptor())
                        .build()
                )
            }
            .build()
            .create(ImgurAPI::class.java)
    }
}