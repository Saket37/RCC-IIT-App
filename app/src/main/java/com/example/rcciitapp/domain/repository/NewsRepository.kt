package com.example.rcciitapp.domain.repository

import com.example.rcciitapp.data.remote.entity.NewsItem
import com.example.rcciitapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(): Flow<Resource<List<List<String>>>>
}