package com.example.rcciitapp.utils

sealed class FacultyUpdateEvent {
    class EmailChanged(val email: String) : FacultyUpdateEvent()
    class NameChanged(val name: String) : FacultyUpdateEvent()
    class DegreeChanged(val degree: String) : FacultyUpdateEvent()
    class DojChanged(val doj: String) : FacultyUpdateEvent()
    class DesignationChanged(val designation: String) : FacultyUpdateEvent()
}
