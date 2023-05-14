package com.example.rcciitapp.ui.updates

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Event() {
    WebViewWrapper(url = "https://www.rcciit.org/updates/events.aspx")
}

@Composable
fun WebViewWrapper(url: String) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }
    }

    AndroidView({ webView }) { view ->
        view.loadUrl(url)
        view.webViewClient = object : WebViewClient() {
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
fun EventsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        //elevation = CardDefaults.cardElevation(6.dp),
        //shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = "Dated: 08 May, 2023 - 17 May, 2023 ")
        Text(text = "Venue: RCCIIT Type: State")
        Text(text = "Select Faculty of RCCIIT will be trained by Intel Experts on AI to become Lead Facilitators of 'AI for Future Workforce' Program to be offered to the students in collaboration with Intel.")
    }
}

@Preview
@Composable
fun EventCardPreview() {
    EventsCard()
}