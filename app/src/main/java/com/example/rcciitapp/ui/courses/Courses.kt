package com.example.rcciitapp.ui.courses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rcciitapp.navigation.Destination
import com.example.rcciitapp.viewModel.CourseState
import com.example.rcciitapp.viewModel.CourseUiState
import com.example.rcciitapp.viewModel.CourseViewModel
import com.example.rcciitapp.viewModel.CoursesViewModel

/**
 * Courses Tab View
 */
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Course(
    modifier: Modifier = Modifier, navController: NavHostController,
) {
    val viewModel: CourseViewModel = hiltViewModel()
    CourseContent(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        navController = navController
    )
}

@Composable
fun CourseContent(
    modifier: Modifier = Modifier,
    uiState: CourseUiState,
    navController: NavHostController = rememberNavController()
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        //contentAlignment = Alignment.Center
    ) {

        //var size by remember { mutableStateOf(Size.Zero) }
        LazyColumn() {
            if (uiState.isLoading) {
                items(10) {
                    ShimmerCourseCard()
                }
            } else {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Text(
                            text = "B.Tech",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )
                    }
                }
                items(uiState.courses) {
                    if (it.branch == "B. Tech") {
                        Box(
                            modifier = Modifier
                                .padding(6.dp)
                            //.aspectRatio(1f)
                            // .onGloballyPositioned { size = it.size.toSize() }
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            CoursesCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .aspectRatio(1f),
                                onNavigate = {
                                    /*navController.navigate(
                                        Destination.Faculty.withArgs(
                                            it.id
                                        )
                                    )*/
                                },
                                title = it.courseName,
                                branch = it.branch,
                                duration = it.duration,
                                intake = it.intake,
                                //size = size,
                            )


                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(modifier = Modifier.padding(24.dp))
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Text(
                            text = "M. Tech",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )
                    }
                }
                items(uiState.courses) {
                    if (it.branch == "M.Tech") {
                        Box(
                            modifier = Modifier
                                .padding(6.dp)
                            //.aspectRatio(1f)
                            // .onGloballyPositioned { size = it.size.toSize() }
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            CoursesCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .aspectRatio(1f),
                                onNavigate = {
                                    /*navController.navigate(
                                        Destination.Faculty.withArgs(
                                            it.id
                                        )
                                    )*/
                                },
                                title = it.courseName,
                                branch = it.branch,
                                duration = it.duration,
                                intake = it.intake,
                                //size = size,
                            )


                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(modifier = Modifier.padding(24.dp))
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "MCA",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )
                    }
                }
                items(uiState.courses) {
                    if (it.branch == "MCA") {
                        Box(
                            modifier = Modifier
                                .padding(6.dp)
                            //.aspectRatio(1f)
                            // .onGloballyPositioned { size = it.size.toSize() }
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            CoursesCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .aspectRatio(1f),
                                onNavigate = {
                                    /*navController.navigate(
                                        Destination.Faculty.withArgs(
                                            it.id
                                        )
                                    )*/
                                },
                                title = it.courseName,
                                branch = it.branch,
                                duration = it.duration,
                                intake = it.intake,
                                //size = size,
                            )


                        }
                    }
                }

            }
        }
    }

}


