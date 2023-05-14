package com.example.rcciitapp.ui.updates

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable

@Composable
fun News() {
    Box(
    ) {
        WebViewWrapper(url = "https://www.rcciit.org/updates/news.aspx")
    }
}