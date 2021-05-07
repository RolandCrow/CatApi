package com.example.catapi.data

import com.squareup.moshi.Json

data class ImageResultData(
    @field:Json(name = "url") val imageUrl: String,
    val breeds: List<CatBreedData> // добавляем все в лист так как ответ сервера структурирован
)
