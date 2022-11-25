package com.example.rcciitapp.data.course.impl

import com.example.rcciitapp.model.Course

class BlockingFakeCourseRepository : CourseRepository {
    override suspend fun getAllCourses(): com.example.rcciitapp.data.Result<List<Course>> {
        return com.example.rcciitapp.data.Result.Success(courses)
    }
}