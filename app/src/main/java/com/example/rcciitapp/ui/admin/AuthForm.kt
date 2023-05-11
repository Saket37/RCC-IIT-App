package com.example.rcciitapp.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AuthForm(
    modifier: Modifier = Modifier,
    email: String?,
    password: String?,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onAuthenticate: () -> Unit,
    enableAuthentication: Boolean,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        AuthenticationTitle()
        Spacer(modifier = Modifier.height(40.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp)
                .padding(horizontal = 32.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                val passwordFocusRequester = FocusRequester()
                Spacer(modifier = Modifier.height(32.dp))
                EmailInput(
                    modifier = Modifier.fillMaxWidth(),
                    email = email ?: "",
                    onEmailChanged = onEmailChanged
                ) {
                    passwordFocusRequester.requestFocus()
                }
                Spacer(modifier = Modifier.height(16.dp))
                PasswordInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester),
                    password = password ?: "",
                    onPasswordChanged = onPasswordChanged
                ) {
                    onAuthenticate()
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End) {
                    AuthButton(
                        onAuthenticate = { onAuthenticate() },
                        enableAuthentication = enableAuthentication
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_AuthenticationForm() {
    MaterialTheme {
        AuthForm(
            modifier = Modifier.fillMaxWidth(),
            email = "contact@gmail.com",
            password = "12345678",
            onEmailChanged = { },
            onPasswordChanged = { },
            onAuthenticate = { },
            enableAuthentication = true,
        )
    }
}