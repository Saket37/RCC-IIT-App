package com.example.rcciitapp.viewModel.event

sealed class FacultyUpdateEvent {
    class EmailChanged(val email: String) : FacultyUpdateEvent()
    class NameChanged(val name: String) : FacultyUpdateEvent()
    class DegreeChanged(val degree: String) : FacultyUpdateEvent()
    class DojChanged(val doj: String) : FacultyUpdateEvent()
    class DesignationChanged(val designation: String) : FacultyUpdateEvent()
   // object Update : FacultyUpdateEvent()
}
