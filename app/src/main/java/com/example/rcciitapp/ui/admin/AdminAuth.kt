package com.example.rcciitapp.ui.admin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rcciitapp.data.remote.entity.AdminAuthState
import com.example.rcciitapp.utils.AdminAuthEvent
import com.example.rcciitapp.viewModel.AuthViewModel

@Composable
fun AdminAuth() {
    val authViewModel: AuthViewModel = viewModel()
    AdminAuthContent(
        modifier = Modifier.fillMaxWidth(),
        adminAuthState = authViewModel.authUiState.collectAsState().value,
        handleEvent = authViewModel::handleAuthEvent
    )
}

@Composable
fun AdminAuthContent(
    modifier: Modifier, adminAuthState: AdminAuthState, handleEvent: (event: AdminAuthEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (adminAuthState.isLoading) {
            CircularProgressIndicator()
        } else {
            AuthForm(authenticationMode = adminAuthState.authenticationMode,
                email = adminAuthState.email,
                password = adminAuthState.password,
                satisfiedRequirements = adminAuthState.passwordRequirements,
                onEmailChanged = { email ->
                    handleEvent(AdminAuthEvent.EmailChanged(email))
                },
                onPasswordChanged = { password ->
                    handleEvent(AdminAuthEvent.PasswordChanged(password))
                },
                onAuthenticate = { handleEvent(AdminAuthEvent.Authenticate) },
                enableAuthentication = adminAuthState.isFormValid(),
                onToggleMode = {
                    handleEvent(AdminAuthEvent.ToggleAuthenticationMode)
                })
            adminAuthState.error?.let { error ->
                AuthenticationErrorDialog(
                    error = error,
                    dismissError = { handleEvent(AdminAuthEvent.ErrorDismissed) })
            }
        }
    }
}

@Preview
@Composable
fun AdminAuthPreview() {
    AdminAuth()
}

@Preview(showBackground = true)
@Composable
fun Preview_AuthenticationContent() {
    MaterialTheme {
        AdminAuthContent(
            modifier = Modifier,
            adminAuthState = AdminAuthState(),
            handleEvent = { })
    }
}