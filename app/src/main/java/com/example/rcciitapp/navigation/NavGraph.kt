package com.example.rcciitapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.rcciitapp.ui.courses.Course
import com.example.rcciitapp.ui.courses.CourseDetailScreen
import com.example.rcciitapp.ui.rccHome.RccHome
import com.example.rcciitapp.ui.updates.Updates

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destination.Home.path,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(startDestination = Destination.RCC.path, route = Destination.Home.path) {
            composable(route = Destination.RCC.path) {
                RccHome(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            composable(route = Destination.Update.path) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Updates()
                }
            }
            composable(route = Destination.Courses.path) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Course(navController = navController)
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
            composable(
                route = Destination.CourseDetails.path +"/{courseId}",
                arguments = listOf(navArgument("courseId") {
                    type = NavType.StringType
                    nullable =false
                })
            ) {
                val courseId = it.arguments?.getString("courseId")
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    courseId?.let { it1 -> CourseDetailScreen(courseId = it1) }
                }
            }
        }
        /*composable(route = Destination.AdminLogin.path) {
            AdminAuth()
        }*/
    }
}
