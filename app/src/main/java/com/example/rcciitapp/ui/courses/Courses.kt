package com.example.rcciitapp.ui.courses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rcciitapp.viewModel.CourseState
import com.example.rcciitapp.viewModel.CoursesViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Course(modifier: Modifier = Modifier) {
    val viewModel: CoursesViewModel = hiltViewModel()
    CourseContent(uiState = viewModel.courseUiState.collectAsStateWithLifecycle().value)
}

@Composable
fun CourseContent(modifier: Modifier = Modifier, uiState: CourseState) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            var size by remember { mutableStateOf(Size.Zero) }
            val gridState = rememberLazyGridState()
            LazyVerticalGrid(columns = GridCells.Fixed(2), state = gridState) {
                items(uiState.course) {
                    Box(
                        modifier = Modifier
                            .padding(6.dp)
                            .aspectRatio(1f)
                            .onGloballyPositioned { size = it.size.toSize() },
                        contentAlignment = Alignment.Center
                    ) {
                        CoursesCard(
                            modifier = Modifier
                                .fillMaxSize()
                                .aspectRatio(1f),
                            onNavigate = { },
                            title = it.courseName,
                            branch = it.branch,
                            duration = it.duration,
                            intake = it.intake,
                            size= size,
                        )


                    }

                }
            }
        }
    }

}


