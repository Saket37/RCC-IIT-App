package com.example.rcciitapp.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.rcciitapp.navigation.Destination

@Composable
fun SplashScreen(navHostController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        /*val composition by rememberLottieComposition(spec =)
        val logoAnimationState = animateLottieCompositionAsState(composition = composition)
        LottieAnimation(composition = composition, progress = { logoAnimationState.progress })
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            navHostController.navigate(Destination.Splash.route)
        }*/
    }
}

@Preview
@Composable
fun SplashScreenPreview() {

}