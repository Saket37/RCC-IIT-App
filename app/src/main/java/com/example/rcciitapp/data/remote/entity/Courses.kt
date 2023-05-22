package com.example.rcciitapp.data.remote.entity

data class Courses(
    val `data`: List<Data>,
    val status: String,
    val message: String?
)

data class Data(
    val __v: Int,
    val _id: String,
    val branch: String,
    val courseName: String,
    val duration: Int,
    val intake: Int,
    val stream: String
)