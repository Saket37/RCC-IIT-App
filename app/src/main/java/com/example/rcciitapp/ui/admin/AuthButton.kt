package com.example.rcciitapp.ui.admin

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.rcciitapp.R
import com.example.rcciitapp.data.remote.entity.AuthenticationMode

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    onAuthenticate: () -> Unit,
    enableAuthentication: Boolean,
) {
    Button(onClick = { onAuthenticate() }, enabled = enableAuthentication) {
        Text(
            modifier = Modifier, text = stringResource(
                id = if (authenticationMode == AuthenticationMode.SIGN_IN) {
                    R.string.action_sign_in
                } else R.string.action_sign_up
            )
        )
    }
}