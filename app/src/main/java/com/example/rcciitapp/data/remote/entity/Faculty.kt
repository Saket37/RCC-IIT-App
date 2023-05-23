package com.example.rcciitapp.data.remote.entity

data class FacultyResponse(
    val `data`: List<Faculty>,
    val status: String
)
data class Faculty(
    val __v: Int,
    val _id: String,
    val degree: String,
    val designation: String,
    val dob: String,
    val email: String,
    val image: String,
    val name: String,
    val stream: String
)