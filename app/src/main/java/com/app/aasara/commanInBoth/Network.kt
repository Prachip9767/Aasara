package com.app.aasara.commanInBoth

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    fun getRetrofit()= Retrofit.Builder().baseUrl("https://run.mocky.io/v3/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun getApiService()= getRetrofit().create(ApiCall::class.java)
}