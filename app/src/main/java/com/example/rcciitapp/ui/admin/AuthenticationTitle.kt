package com.example.rcciitapp.ui.admin

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.rcciitapp.R

@Composable
fun AuthenticationTitle(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(
            R.string.label_sign_in_to_account
        ), fontSize = 24.sp, fontWeight = FontWeight.Black
    )
}