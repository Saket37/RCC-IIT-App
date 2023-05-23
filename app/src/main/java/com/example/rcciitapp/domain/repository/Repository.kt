package com.example.rcciitapp.domain.repository

import com.example.rcciitapp.data.remote.entity.Courses
import com.example.rcciitapp.data.remote.entity.FacultyResponse
import com.example.rcciitapp.data.remote.entity.Login
import com.example.rcciitapp.data.remote.entity.LoginResponse
import com.example.rcciitapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun adminLogin(login: Login): Flow<Resource<LoginResponse>>
    suspend fun getCourses(): Flow<Resource<Courses>>
    suspend fun getFaculty(stream:String):Flow<Resource<FacultyResponse>>
}