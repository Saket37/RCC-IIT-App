package com.example.rcciitapp.ui.admin

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.rcciitapp.R

@Composable
fun AuthenticationErrorDialog(
    modifier: Modifier = Modifier,
    error: String,
    dismissError: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { dismissError() },
        confirmButton = {
            TextButton(onClick = { dismissError() }) {
                Text(text = stringResource(id = R.string.error_action))
            }
        },
        text = { Text(text = error) },
        title = {
            Text(text = stringResource(id = R.string.error_title), fontSize = 18.sp)
        }, properties = DialogProperties(dismissOnClickOutside = false)
    )
}