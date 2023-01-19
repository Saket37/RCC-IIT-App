package com.example.rcciitapp.observeconnectivity

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}