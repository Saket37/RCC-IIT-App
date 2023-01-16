package com.example.rcciitapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rcciitapp.R
import kotlinx.coroutines.delay


@Composable
fun ConnectivityStatus(isConnected: Boolean) {
    var visibility by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visibility,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        ConnectivityStatusBox(isConnected = isConnected)
    }
    LaunchedEffect(isConnected) {
        visibility = if (!isConnected) {
            true
        } else {
            delay(2000)
            false
        }
    }
}

@Composable
fun ConnectivityStatusBox(isConnected: Boolean) {
    val backgroundColor by animateColorAsState(if (isConnected) Color.Green else MaterialTheme.colorScheme.error)
    val message = if (isConnected) "Back Online!" else "No Internet Connection!"
    val iconResource = if (isConnected) {
        R.drawable.ic_connectivity_available
    } else {
        R.drawable.ic_connectivity_unavailable
    }
    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = "Connectivity Icon",
                tint = Color.White
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = message, color = Color.White, fontSize = 15.sp)
        }
    }
}