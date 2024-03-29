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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.rcciitapp.navigation.Navigation
import com.example.rcciitapp.ui.home.RccApp
import com.example.rcciitapp.ui.theme.RCCIITAppTheme
import com.example.rcciitapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO Add texts in splash screen
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.homeUiState.value.isLoading }
        }
        val workRequest = 
        setContent {
            RCCIITAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    //LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                    RccApp(
                        isConnected = viewModel.homeUiState.collectAsStateWithLifecycle().value.isConnectivityAvailable,
                        isAdminLoggedIn =
                        viewModel.homeUiState.collectAsStateWithLifecycle().value.isAdminLoggedIn,
                        handleEvent = viewModel::handleEvent
                    )
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
