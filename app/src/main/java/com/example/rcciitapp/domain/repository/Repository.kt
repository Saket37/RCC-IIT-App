package com.example.rcciitapp.domain.repository

import com.example.rcciitapp.data.remote.entity.*
import com.example.rcciitapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun adminLogin(login: Login): Flow<Resource<LoginResponse>>
    suspend fun getCourses(): Flow<Resource<Courses>>
    suspend fun getFaculty(stream: String): Flow<Resource<FacultyResponse>>
    suspend fun deleteFaculty(id: String): Flow<Resource<DeleteFacultyResponse>>
}