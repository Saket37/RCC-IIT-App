package com.example.rcciitapp.data.remote

import com.example.rcciitapp.data.remote.entity.Login
import com.example.rcciitapp.data.remote.entity.LoginResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("users/login")
    suspend fun login(
        @Body login: Login,
        //@Body requestBody: RequestBody
    ):LoginResponse
}