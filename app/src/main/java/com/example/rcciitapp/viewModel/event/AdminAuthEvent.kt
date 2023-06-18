package com.example.rcciitapp.viewModel.event

sealed class AdminAuthEvent {

    //object ToggleAuthenticationMode : AdminAuthEvent()

    class EmailChanged(val email: String) : AdminAuthEvent()

    class PasswordChanged(val password: String) : AdminAuthEvent()

    object Authenticate : AdminAuthEvent()

    object ErrorDismissed : AdminAuthEvent()
}