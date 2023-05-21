package com.example.rcciitapp.ui.updates

import android.graphics.Bitmap
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rcciitapp.R
import com.example.rcciitapp.viewModel.EventViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Event() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val viewModel: EventViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly,) {


            EventCardStatic(
                title = "\"FDP on AI by Intel\"",
                date = "Dated: 08 May, 2023 - 17 May, 2023",
                description = "Select Faculty of RCCIIT will be trained by Intel Experts on AI to become Lead Facilitators of 'AI for Future Workforce' Program to be offered to the students in collaboration with Intel.",
                venue = "Venue : RCCIIT"
            )
            Spacer(modifier = Modifier.height(12.dp))
            EventCardStatic(
                title = "\"Annual Cultural Fest - Regalia '23\"",
                date = "Dated: 20 Apr, 2023 - 28 Apr, 2023",
                description = "The Annual Cultural Fest organized as State Level Inter-College Cultural Meet",
                venue = "Venue : RCCIIT (prelims) & Sarat Sadan (finale)"
            )
            Spacer(modifier = Modifier.height(12.dp))
            EventCardStatic(
                title = "\"Smart Bengal Hackathon - SBH '23\"",
                date = "Dated: 10 Apr, 2023 - 12 Apr, 2023",
                description = "The IIC and SWC of RCCIIT are organizing a State level Smart Bengal Hackathon 2023 for School (SBH Junior) and College students (SBH Senior) throughout WB as part of Annual Tech Fest (Techtrix 2023).\n",
                venue = "Venue : RCCIIT"
            )}
        /*LazyColumn() {
            items(uiState.event) {
                EventsCard(title = it.title, date = it.date, venue = it.venue, type = it.type)
            }
        }*/
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

@Composable
fun EventCardStatic(title: String, date: String, description: String, venue: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_events), contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = date, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = venue, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = description)

            }
        }

    }
}


@Preview
@Composable
fun EventCardPreview() {
    EventCardStatic(
        title = "\"FDP on AI by Intel\"",
        date = "Dated: 08 May, 2023 - 17 May, 2023",
        description = "Select Faculty of RCCIIT will be trained by Intel Experts on AI to become Lead Facilitators of 'AI for Future Workforce' Program to be offered to the students in collaboration with Intel.",
        venue = "RCCIIT"
    )
}

@Preview
@Composable
fun EventPreview() {
    Event()
}