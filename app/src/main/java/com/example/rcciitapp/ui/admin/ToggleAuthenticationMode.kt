package com.example.rcciitapp.ui.admin

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.R
import com.example.rcciitapp.data.remote.entity.AuthenticationMode

@Composable
fun ToggleAuthenticationMode(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    toggleAuthentication: () -> Unit
) {
    Surface(modifier = modifier, elevation = 8.dp) {
        TextButton(onClick = {toggleAuthentication()}) {
            Text(text = stringResource(
                id = if (authenticationMode == AuthenticationMode.SIGN_IN)
                    R.string.action_need_account
                else R.string.action_already_have_account
            ))
        }
    }
}