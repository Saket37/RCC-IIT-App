package com.example.rcciitapp.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.rcciitapp.ui.faculty.EditFacultyScreen
import com.example.rcciitapp.ui.faculty.FacultyScreen
import com.example.rcciitapp.ui.gallery.GalleryScreen
import com.example.rcciitapp.ui.placement.PlacementScreen
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
                    GalleryScreen()
                }
            }
            composable(
                route = Destination.Faculty.path + "/{stream}",
                arguments = listOf(navArgument("stream") {
                    type = NavType.StringType
                    nullable = false
                })
            ) {
                val stream = it.arguments?.getString("stream")
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    stream?.let { it1 ->
                        FacultyScreen(
                            stream = it1,
                            navController = navController
                        )
                    }
                }
            }
            composable(route = Destination.EditFaculty.path + "/{_id}/{name}/{email}/{doj}/{degree}/{designation}",
                arguments = listOf(
                    navArgument("_id") {
                        type = NavType.StringType
                        nullable = false
                    },
                    navArgument("name") {
                        type = NavType.StringType
                        nullable = false
                    },
                    navArgument("email") {
                        type = NavType.StringType
                        nullable = false
                    },
                    navArgument("doj") {
                        type = NavType.StringType
                        nullable = false
                    },
                    navArgument("degree") {
                        type = NavType.StringType
                        nullable = false
                    },
                    navArgument("designation") {
                        type = NavType.StringType
                        nullable = false
                    }
                )

            ) { backStackEntry ->
                val facultyId = backStackEntry.arguments?.getString("_id")
                val facultyName = backStackEntry.arguments?.getString("name")
                val facultyEmail = backStackEntry.arguments?.getString("email")
                val facultyDoj = backStackEntry.arguments?.getString("doj")
                val facultyDegree = backStackEntry.arguments?.getString("degree")
                val facultyDesignation = backStackEntry.arguments?.getString("designation")

                Log.d("FACULTY_ID", facultyId.toString())
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    facultyId?.let { id ->
                        facultyName?.let { name ->
                            facultyDegree?.let {
                                facultyEmail?.let { email ->
                                    facultyDoj?.let { doj ->
                                        facultyDesignation?.let { designation ->
                                            EditFacultyScreen(
                                                id = id,
                                                name = name,
                                                email = email,
                                                doj = doj,
                                                degree = it,
                                                designation = designation,
                                                navController = navController
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }



            composable(route = Destination.AddFaculty.path) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    //EditFacultyScreen()
                }
            }
            composable(Destination.Placement.path) {
                Box(modifier = Modifier.fillMaxSize()) {
                    PlacementScreen(
                        navController = navController
                    )
                }
            }
        }

/*composable(route = Destination.AdminLogin.path) {
    AdminAuth()
}*/
    }

}
