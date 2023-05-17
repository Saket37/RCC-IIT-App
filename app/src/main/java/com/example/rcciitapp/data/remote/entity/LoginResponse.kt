package com.example.rcciitapp.data.remote.entity

data class LoginResponse(
    val status: String,
    val token: String?,
    val user: User?,
    val message: String?
)