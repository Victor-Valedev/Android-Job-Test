package com.victor.androidjobtest.model

data class ResponseImgur(
    val `data`: List<Data>,
    val status: Int,
    val success: Boolean
)