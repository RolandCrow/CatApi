package com.example.catapi.data

import com.squareup.moshi.Json

data class UserData(
    @field:Json(name = "fn") val firstName: String, // используем Moshi для парсинга JSON
    @field:Json(name = "last") val lastName: String
)
