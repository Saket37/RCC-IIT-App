package com.example.rcciitapp.ui.courses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rcciitapp.viewModel.CourseState
import com.example.rcciitapp.viewModel.CoursesViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CourseDetailScreen(modifier: Modifier = Modifier,courseId:String) {
    val viewModel: CoursesViewModel = hiltViewModel()
    viewModel.selectCourse(courseId)
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        FacultySection(uiState = viewModel.courseUiState.collectAsStateWithLifecycle().value)
    }
}

@Composable
fun FacultySection(modifier: Modifier = Modifier, uiState: CourseState) {
    val uiFacultyState = uiState.isSelectedCourse

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
        CircularProgressIndicator()}
    } else {
        LazyColumn() {
            if (uiFacultyState != null) {
                items(uiFacultyState.faculty) {
                    FacultyCard(faculty = it)
                }
            }
        }
    }
}