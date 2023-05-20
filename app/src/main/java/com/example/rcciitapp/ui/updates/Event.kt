package com.example.rcciitapp.ui.updates

import android.graphics.Bitmap
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rcciitapp.viewModel.EventViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Event() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val viewModel: EventViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Box(
    ) {
        LazyColumn() {
            items(uiState.event) {
                EventsCard(title = it.title, date = it.date, venue = it.venue, type = it.type)
            }
        }
        //WebViewWrapper(url = "https://www.rcciit.org/updates/events.aspx")

    }
}

@Composable
fun WebViewWrapper(url: String) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
                // Enable horizontal scrolling
                isHorizontalScrollBarEnabled = true
                setOnTouchListener { _, _ -> false }
            }
            webViewClient = WebViewClient()
        }
    }

    AndroidView({ webView }) { view ->
        view.loadUrl(url)
        view.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                // Inject JavaScript code to manipulate the webpage
                val javascript = """
                    // Replace this JavaScript code with your logic to extract the desired part of the webpage
                    // For example, you can use document.getElementById() to select specific elements
                    
                    // Example: Extract the content of an element with the ID "myElement"
                    var myElement = document.getElementByClass('.ipcontainer3');
                    var extractedContent = myElement ? myElement.innerHTML : "";
                    
                    // Send the extracted content back to the Android app
                    window.ReactNativeWebView.postMessage(extractedContent);
                """
                view?.evaluateJavascript(javascript, null)
            }
        }
        //view.addJavascriptInterface(WebAppInterface(view.context), "Android")
    }

    DisposableEffect(Unit) {
        onDispose {
            webView.destroy()
        }
    }
}

@Composable
fun EventsCard(
    title: String,
    date: String,
    venue: String,
    type: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        //elevation = CardDefaults.cardElevation(6.dp),
        //shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = date)
        Text(text = venue)
        Text(text = title)
    }
}

/*
@Preview
@Composable
fun EventCardPreview() {
    EventsCard()
}*/
