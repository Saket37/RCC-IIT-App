package com.example.rcciitapp.data.remote.entity

data class Courses(
    val `data`: List<CourseData>,
    val status: String,
    val message: String?
)

data class CourseData(
    val __v: Int,
    val _id: String,
    val branch: String,
    val courseName: String,
    val duration: Int,
    val intake: Int,
    val stream: String
)