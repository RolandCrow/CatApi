package com.example.catapi.api


import com.example.catapi.data.ImageResultData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheCatApiService {
    @GET("images/search") // запрос на сайт
    fun searchImages(
        @Query("limit") limit: Int, // query становится частью запроса
        @Query("size") format: String
    ) : Call<List<ImageResultData>> // вызов в лист так как ответ сервера сложный


}