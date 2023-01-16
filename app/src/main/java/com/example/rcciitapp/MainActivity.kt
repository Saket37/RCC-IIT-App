package com.example.rcciitapp

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.rcciitapp.navigation.Navigation
import com.example.rcciitapp.observeconnectivity.ConnectivityObserver
import com.example.rcciitapp.observeconnectivity.NetworkConnectivityObserver
import com.example.rcciitapp.ui.home.RccApp
import com.example.rcciitapp.ui.theme.RCCIITAppTheme
import com.example.rcciitapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        // TODO Add texts in splash screen
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.isLoading.value }
        }
        setContent {
            RCCIITAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val status by connectivityObserver.observe()
                        .collectAsState(initial = ConnectivityObserver.Status.Available)
                    val isConnected: Boolean = status === ConnectivityObserver.Status.Available

                    //LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                    RccApp(isConnected = isConnected)
                }
            }
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose { }
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose { activity.requestedOrientation = originalOrientation }

    }
}
