package com.example.rcciitapp.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Login(
    @field:Json(name = "email") val email: String,
    @field:Json(name = "password") val password: String
)