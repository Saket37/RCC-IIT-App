package com.example.rcciitapp.ui.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.rcciitapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    password: String?,
    onPasswordChanged: (password: String) -> Unit,
    onDoneClicked: () -> Unit,
) {
    var isPasswordHidden by remember {
        mutableStateOf(true)
    }
    TextField(
        value = password ?: "",
        onValueChange = { onPasswordChanged(it) },
        singleLine = true,
        label = { Text(text = stringResource(id = R.string.label_password)) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null)
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable(
                    onClickLabel = if (isPasswordHidden) {
                        stringResource(id = R.string.cd_show_password)
                    } else stringResource(id = R.string.cd_hide_password)
                ) {
                    isPasswordHidden = !isPasswordHidden
                }, painter = if (isPasswordHidden) {
                    painterResource(id = R.drawable.ic_visibility)
                } else painterResource(id = R.drawable.ic_visibility_off), contentDescription = null
            )
        },
        visualTransformation = if (isPasswordHidden) {
            PasswordVisualTransformation()
        } else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ), keyboardActions = KeyboardActions(onDone = { onDoneClicked() })
    )

}

@Preview(showBackground = true)
@Composable
fun Preview_PasswordInput() {
    MaterialTheme {
        PasswordInput(
            modifier = Modifier.fillMaxWidth(),
            password = "12345678",
            onPasswordChanged = { },
            onDoneClicked = { }
        )
    }
}