package com.example.rcciitapp.domain.repository

import com.example.rcciitapp.data.remote.entity.Event
import com.example.rcciitapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEvents(): Flow<Resource<List<Event>>>
}