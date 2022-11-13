package com.example.rcciitapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rcciitapp.R

@Composable
fun ColumnScope.DrawerContent(modifier: Modifier = Modifier) {
    // TODO - Add image, admin login and other tabs
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.app_name),
        fontSize = 20.sp
    )
    Spacer(modifier = Modifier.height(8.dp))
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}