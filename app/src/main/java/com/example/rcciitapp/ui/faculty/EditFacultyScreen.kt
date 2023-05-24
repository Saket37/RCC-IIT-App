package com.example.rcciitapp.ui.faculty

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.rcciitapp.navigation.Destination
import com.example.rcciitapp.ui.components.EditFacultyAppBar
import com.example.rcciitapp.ui.theme.md_theme_light_surface
import com.example.rcciitapp.utils.FacultyUpdateEvent
import com.example.rcciitapp.viewModel.EditFacultyUiState
import com.example.rcciitapp.viewModel.FacultyScreenViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun EditFacultyScreen(
    id: String,
    navController: NavHostController,
    name: String,
    email: String,
    doj: String,
    degree: String,
    designation: String, stream: String

) {
    val viewModel: FacultyScreenViewModel = hiltViewModel()
    Log.d("EditFacultyScreen_id", id)
    //viewModel.fetchEditFacultyData(id = id)
    val uiState = viewModel.editUiState.collectAsState()
    uiState.value.name = name
    uiState.value.degree = degree
    uiState.value.email = email
    uiState.value.doj = doj
    uiState.value.designation = designation
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    Scaffold(
        topBar = {
            EditFacultyAppBar(
                title = "Edit",
                onCancelled = { navController.popBackStack() },
                onSaveClicked = {
                    viewModel.patchUpdateFaculty()
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
            //viewModel.fetchEditFacultyData(id)
            EditFaculty(
                uiState = uiState.value,
                handleEvent = viewModel::handleEditEvent
            )
        }
    }
}


@Composable
fun EditFaculty(
    uiState: EditFacultyUiState,
    handleEvent: (event: FacultyUpdateEvent) -> Unit,
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
        TextInput(
            icon = Icons.Default.Person,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            label = "Name",
            text = uiState.name,
            onTextChanged = { handleEvent(FacultyUpdateEvent.NameChanged(it)) },
            onNextClicked = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(
            icon = Icons.Default.Email,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            label = "Email",
            text = uiState.email,
            onTextChanged = { handleEvent(FacultyUpdateEvent.EmailChanged(it)) },
            onNextClicked = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(
            icon = Icons.Default.Email,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            label = "Degree",
            text = uiState.degree,
            onTextChanged = { handleEvent(FacultyUpdateEvent.DegreeChanged(it)) },
            onNextClicked = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(
            icon = Icons.Default.Email,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            label = "Designation",
            text = uiState.designation,
            onTextChanged = { handleEvent(FacultyUpdateEvent.DesignationChanged(it)) },
            onNextClicked = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(
            icon = Icons.Default.Email,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            label = "DOJ",
            text = uiState.doj,
            onTextChanged = { handleEvent(FacultyUpdateEvent.DojChanged(it)) },
            onNextClicked = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    icon: ImageVector,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    label: String,
    modifier: Modifier = Modifier,
    text: String?,
    onTextChanged: (text: String) -> Unit,
    onNextClicked: () -> Unit,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text ?: "",
        onValueChange = { onTextChanged(it) },
        label = {
            Text(text = label)
        },
        singleLine = true,
        leadingIcon = {
            androidx.compose.material3.Icon(imageVector = icon, contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction, keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(onNext = { onNextClicked() }),
        colors = TextFieldDefaults.textFieldColors(containerColor = md_theme_light_surface)
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoPicker(
    modifier: Modifier = Modifier,
) {
    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImage = uri }
    )
    Surface(color = Color.LightGray, shape = CircleShape, modifier = modifier, onClick = {
        singlePhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }) {
        //TODO Add a placeholder for image

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(selectedImage).build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
    }
}


@Preview
@Composable
fun EditFacultyAppBarPreview() {
    EditFacultyAppBar(title = "Create", onCancelled = { }) {

    }
}

/*
@Preview
@Composable
fun EditFacultyScreenPreview() {
    EditFacultyScreen(id = "")
}*/
