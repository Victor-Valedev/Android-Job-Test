package com.victor.androidjobtest.api

import com.victor.androidjobtest.model.ResponseImgur
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurAPI {

    @GET("gallery/search/?")
    suspend fun searchImageGallery(
        @Query("q") q: String
    ): Response<ResponseImgur>

}