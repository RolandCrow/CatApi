package com.example.catapi.api

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class Constants {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .addConverterFactory(ScalarsConverterFactory.create()) // обработка сообщений с сервера
        .build()

    val catApiService = retrofit.create(TheCatApiService::class.java)
}