package com.example.rcciitapp.data.course.impl

import com.example.rcciitapp.model.Course
import dagger.Provides

interface CourseRepository {
    suspend fun getAllCourses(): com.example.rcciitapp.data.Result<List<Course>>
}