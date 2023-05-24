package com.example.rcciitapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RccTopAppBar(
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.ic_app_bar_icon),
                    contentDescription = null
                )
                Text(text = "RCC IIT")
            }
        },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.cd_open_menu)
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacultyAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onCancelled: () -> Unit,
    onAddClicked: () -> Unit,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState),
    isAdminLoggedIn: Boolean
) {
    CenterAlignedTopAppBar(title = { Text(text = title) }, navigationIcon = {
        IconButton(onClick = { onCancelled() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
    }, actions = {
        if (isAdminLoggedIn)
            IconButton(onClick = { onAddClicked() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFacultyAppBar(
    modifier: Modifier = Modifier, title: String, onCancelled: () -> Unit, onSaveClicked: () -> Unit
) {
    CenterAlignedTopAppBar(title = { Text(text = title) }, navigationIcon = {
        IconButton(onClick = { onCancelled() }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
        }
    }, actions = {
        Button(onClick = { onSaveClicked() }) {
            Text(
                modifier = Modifier, text = stringResource(
                    id = R.string.action_save
                )
            )
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildAppBar(modifier: Modifier = Modifier, title: String, onCancelled: () -> Unit,) {
    CenterAlignedTopAppBar(title = { Text(text = title) }, navigationIcon = {
        IconButton(onClick = { onCancelled() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
    })


}