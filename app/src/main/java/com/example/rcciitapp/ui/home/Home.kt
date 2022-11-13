package com.example.rcciitapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rcciitapp.R
import com.example.rcciitapp.navigation.Destination
import com.example.rcciitapp.navigation.Navigation
import com.example.rcciitapp.ui.bottomNavBar.BottomNavBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState)
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry.value?.destination?.route?.let {
                Destination.fromString(it)
            } ?: run {
                Destination.Home
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text(text = "RCC") }, navigationIcon = {
                IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(id = R.string.cd_open_menu)
                    )
                }
            })
        },
        bottomBar = {
            BottomNavBar(currentDestination = currentDestination, onNavigate = {
                navController.navigate(it.path) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        },
        drawerContent = { DrawerContent(modifier = Modifier.fillMaxWidth()) }
    ) {
        Navigation(modifier = Modifier, navController = navController)
    }
}
