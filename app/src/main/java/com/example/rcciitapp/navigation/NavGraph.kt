package com.example.rcciitapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.rcciitapp.ui.admin.AdminAuth

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destination.Home.path,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(startDestination = Destination.RCC.path, route = Destination.Home.path) {
            composable(route = Destination.RCC.path) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "RCC HOME")
                }
            }
            composable(route = Destination.Notice.path) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Notice")
                }
            }
            composable(route = Destination.Faculty.path) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Faculty")
                }
            }
            composable(route = Destination.Gallery.path) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Gallery")
                }
            }
        }
        composable(route = Destination.AdminLogin.path) {
            AdminAuth()
        }
    }
}
