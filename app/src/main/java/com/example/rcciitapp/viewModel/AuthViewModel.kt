package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.data.remote.entity.AdminAuthState
import com.example.rcciitapp.data.remote.entity.AuthenticationMode
import com.example.rcciitapp.data.remote.entity.PasswordRequirements
import com.example.rcciitapp.utils.AdminAuthEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val _authUiState = MutableStateFlow(AdminAuthState())
    val authUiState get() = _authUiState

    private fun toggleAuthenticationMode() {
        val authenticationMode = _authUiState.value.authenticationMode
        val newAuthMode = if (authenticationMode == AuthenticationMode.SIGN_IN) {
            AuthenticationMode.SIGN_UP
        } else {
            AuthenticationMode.SIGN_IN
        }
        _authUiState.value = _authUiState.value.copy(authenticationMode = newAuthMode)
    }

    private fun updateEmail(email: String) {
        _authUiState.value = _authUiState.value.copy(email = email)
    }

    private fun authenticate() {
        _authUiState.value = _authUiState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            withContext(Dispatchers.Main) {
                _authUiState.value =
                    _authUiState.value.copy(isLoading = false, error = "Something went wrong")
            }
        }
    }

    private fun dismissError() {
        _authUiState.value = _authUiState.value.copy(error = null)
    }

    private fun updatePassword(password: String) {
        val requirements = mutableListOf<PasswordRequirements>()
        if (password.length > 7) {
            requirements.add(PasswordRequirements.EIGHT_CHARACTERS)
        }
        if (password.any { it.isDigit() }) {
            requirements.add(PasswordRequirements.NUMBER)
        }
        if (password.any { it.isUpperCase() }) {
            requirements.add(PasswordRequirements.CAPITAL_LETTER)
        }

        _authUiState.value =
            _authUiState.value.copy(password = password, passwordRequirements = requirements)
    }

    fun handleAuthEvent(authEvent: AdminAuthEvent) {
        when (authEvent) {
            is AdminAuthEvent.ToggleAuthenticationMode -> {
                toggleAuthenticationMode()
            }
            is AdminAuthEvent.EmailChanged -> {
                updateEmail(authEvent.email)
            }
            is AdminAuthEvent.PasswordChanged -> {
                updatePassword(authEvent.password)
            }
            is AdminAuthEvent.ErrorDismissed -> {
                dismissError()
            }
            is AdminAuthEvent.Authenticate -> {
                authenticate()
            }
        }
    }
}