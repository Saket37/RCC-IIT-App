package com.example.rcciitapp.data.course.impl

import com.example.rcciitapp.model.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeCourseRepository @Inject constructor(): CourseRepository {
    override suspend fun getAllCourses(): com.example.rcciitapp.data.Result<List<Course>> {
        return withContext(Dispatchers.IO) {
            delay(800) // pretend we're on a slow network
            if (shouldRandomlyFail()) {
                com.example.rcciitapp.data.Result.Error(IllegalStateException())
            } else {
                com.example.rcciitapp.data.Result.Success(courses)
            }
        }
    }

    // used to drive "random" failure in a predictable pattern, making the first request always
    // succeed
    private var requestCount = 0

    /**
     * Randomly fail some loads to simulate a real network.
     *
     * This will fail deterministically every 5 requests
     */
    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}