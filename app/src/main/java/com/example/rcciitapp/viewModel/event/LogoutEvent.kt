package com.example.rcciitapp.viewModel.event

sealed class LogoutEvent {
    object Logout : LogoutEvent()
}