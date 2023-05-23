package com.example.rcciitapp.data.remote

import com.example.rcciitapp.data.remote.entity.*
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
    ):FacultyResponse
}