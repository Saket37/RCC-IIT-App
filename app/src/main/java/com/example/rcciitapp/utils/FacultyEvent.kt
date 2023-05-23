package com.example.rcciitapp.utils

sealed class FacultyEvent {
    data class DeleteFaculty(val id: String) : FacultyEvent()
}
