package com.example.rcciitapp.ui.faculty

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rcciitapp.navigation.Destination
import com.example.rcciitapp.ui.components.EditFacultyAppBar
import com.example.rcciitapp.utils.AddFacultyEvent
import com.example.rcciitapp.viewModel.AddFacultyUiState
import com.example.rcciitapp.viewModel.AddFacultyViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun AddScreen(
    stream: String,
    navController: NavHostController,
) {
    val viewModel: AddFacultyViewModel = hiltViewModel()
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    Scaffold(
        topBar = {
            EditFacultyAppBar(title = "Edit",
                onCancelled = { navController.popBackStack() },
                onSaveClicked = {
                    viewModel.submit()
                    navController.navigate(
                        Destination.Faculty.withArgs(
                            stream
                        )
                    )
                })
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(
                    0.dp,
                    innerPadding.calculateTopPadding(),
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            AddFaculty(
                uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
                handleEvent = viewModel::handleEditEvent
            )
        }
    }
}

@Composable
fun AddFaculty(
    uiState: AddFacultyUiState,
    handleEvent: (event: AddFacultyEvent) -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(32.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            PhotoPicker(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(icon = Icons.Default.Person,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            label = "Name",
            text = uiState.name,
            onTextChanged = { handleEvent(AddFacultyEvent.NameChanged(it)) },
            onNextClicked = {})
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(icon = Icons.Default.Email,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            label = "Email",
            text = uiState.email,
            onTextChanged = { handleEvent(AddFacultyEvent.EmailChanged(it)) },
            onNextClicked = {})
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(icon = Icons.Default.Email,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            label = "Degree",
            text = uiState.degree,
            onTextChanged = { handleEvent(AddFacultyEvent.DegreeChanged(it)) },
            onNextClicked = {})
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(icon = Icons.Default.Email,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            label = "Designation",
            text = uiState.designation,
            onTextChanged = { handleEvent(AddFacultyEvent.DesignationChanged(it)) },
            onNextClicked = {})
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(icon = Icons.Default.Email,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            label = "DOJ",
            text = uiState.doj,
            onTextChanged = { handleEvent(AddFacultyEvent.DojChanged(it)) },
            onNextClicked = {})
    }
}


