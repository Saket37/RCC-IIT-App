package com.example.rcciitapp.data.remote.entity

data class EditFaculty(
    val status: String,
    val message: String?,
    val `data`: EditFacultyResponse
)

data class EditFacultyResponse(
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

data class EditFacultyBody(
    val degree: String,
    val designation: String,
    val dob: String,
    val email: String,
    //val image: String,
    val name: String,
   // val stream: String
)