package com.example.rcciitapp.ui.admin

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.R

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    onAuthenticate: () -> Unit,
    enableAuthentication: Boolean,
) {
    val iconColor = if (enableAuthentication) Color.White else Color.Gray
    Button(onClick = { onAuthenticate() }, enabled = enableAuthentication) {
        Text(
            modifier = Modifier, text = stringResource(
                id =
                R.string.action_sign_in
            )
        )
        Spacer(modifier = Modifier.width(6.dp))
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Login",
            tint = iconColor
        )
    }
}