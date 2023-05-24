package com.example.rcciitapp.data.repository

import com.example.rcciitapp.data.remote.ApiService
import com.example.rcciitapp.data.remote.entity.*
import com.example.rcciitapp.domain.repository.Repository
import com.example.rcciitapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {
    override suspend fun adminLogin(login: Login): Flow<Resource<LoginResponse>> = flow {

        try {
            val resp = apiService.login(login)
            emit(Resource.success(resp))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message.toString()))
        }
    }

    override suspend fun getCourses(): Flow<Resource<Courses>> = flow {
        try {
            val resp = apiService.getCourses()
            emit(Resource.success(resp))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message.toString()))
        }
    }

    override suspend fun getFaculty(stream: String): Flow<Resource<FacultyResponse>> = flow {
        try {
            val resp = apiService.postFacultyData(stream = stream)
            emit(Resource.success(resp))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message.toString()))
        }
    }

    override suspend fun deleteFaculty(id: String): Flow<Resource<DeleteFacultyResponse>> = flow {
        try {
            val resp = apiService.deleteFaculty(id)
            emit(Resource.success(resp))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message.toString()))
        }
    }

    override suspend fun editFaculty(editFacultyBody: EditFacultyBody): Flow<Resource<EditFaculty>> =
        flow {
            try {
                val resp = apiService.editFaculty(editFacultyBody)
                emit(Resource.success(resp))
            } catch (e: Exception) {
                emit(Resource.error(null, e.message.toString()))
            }
        }
}