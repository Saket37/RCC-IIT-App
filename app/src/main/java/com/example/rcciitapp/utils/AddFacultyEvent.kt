package com.example.rcciitapp.utils

sealed class AddFacultyEvent {
    class EmailChanged(val email: String) : AddFacultyEvent()
    class NameChanged(val name: String) : AddFacultyEvent()
    class DegreeChanged(val degree: String) : AddFacultyEvent()
    class DojChanged(val doj: String) : AddFacultyEvent()
    class DesignationChanged(val designation: String) : AddFacultyEvent()
}
