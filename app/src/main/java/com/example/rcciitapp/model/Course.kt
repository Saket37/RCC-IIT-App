package com.example.rcciitapp.model

import androidx.annotation.DrawableRes

data class Course(
    val id: String,
    val courseName: String,
    val branch: String,
    val duration: String,
    val intake: String,
    val faculty: List<Faculty> = emptyList()
)

data class Faculty(
    val id: String,
    val name: String,
    val degree: String,
    val doj: String,
    val designation: String,
    val email: String,
    @DrawableRes val image: Int,
)