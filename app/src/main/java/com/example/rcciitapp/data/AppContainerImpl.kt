package com.example.rcciitapp.data

import android.content.Context
import com.example.rcciitapp.data.course.impl.CourseRepository
import com.example.rcciitapp.data.course.impl.FakeCourseRepository

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val courseRepository: CourseRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */

class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val courseRepository: CourseRepository by lazy { FakeCourseRepository() }
}