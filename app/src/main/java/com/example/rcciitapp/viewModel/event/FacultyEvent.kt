package com.example.rcciitapp.viewModel.event

sealed class FacultyEvent {
    data class DeleteFaculty(val id: String) : FacultyEvent()
}
