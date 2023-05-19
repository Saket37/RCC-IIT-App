package com.example.rcciitapp.utils

sealed class LogoutEvent {
    object Logout : LogoutEvent()
}