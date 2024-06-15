package com.victor.androidjobtest.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurAPI {

    @GET("gallery/search/?")
    suspend fun searchImageGallery(
        @Query("q") q: String
    )

}