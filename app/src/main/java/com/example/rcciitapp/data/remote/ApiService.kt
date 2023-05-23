package com.example.rcciitapp.data.remote

import com.example.rcciitapp.data.remote.entity.Courses
import com.example.rcciitapp.data.remote.entity.Login
import com.example.rcciitapp.data.remote.entity.LoginResponse
import retrofit2.http.*

interface ApiService {
    @POST("users/login")
    suspend fun login(
        @Body login: Login,
        //@Body requestBody: RequestBody
    ): LoginResponse

    @GET("courses")
    suspend fun getCourses(
        @Header("Authorization") token: String
    ):Courses

    @POST("faculty/{stream}")
    suspend fun postFacultyData(
        @Path("stream") stream: String,
    )
}