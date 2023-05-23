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
    ): Courses

    @POST("faculty/{stream}")
    suspend fun postFacultyData(
        @Path("stream") stream: String,
    ): FacultyResponse

    @DELETE("faculty/delete/{id}")
    suspend fun deleteFaculty(
        @Path("id") id: String
    ): DeleteFacultyResponse
}