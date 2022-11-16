package com.example.rcciitapp.ui.admin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.rcciitapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    email: String?,
    onEmailChanged: (email: String) -> Unit,
    onNextClicked: () -> Unit,
) {
    TextField(value = email ?: "", onValueChange = { onEmailChanged(it) }, label = {
        Text(text = stringResource(id = R.string.label_email))
    }, singleLine = true, leadingIcon = {
        Icon(imageVector = Icons.Default.Email, contentDescription = null)
    }, keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next, keyboardType = KeyboardType.Email
    ), keyboardActions = KeyboardActions(onNext = { onNextClicked() })
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_EmailInput() {
    MaterialTheme {
        EmailInput(
            modifier = Modifier.fillMaxWidth(),
            email = "contact@gmail.com",
            onEmailChanged = { },
            onNextClicked = { }
        )
    }
}
