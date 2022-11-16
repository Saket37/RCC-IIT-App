package com.example.rcciitapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.DrawerDefaults.scrimColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rcciitapp.R
import com.example.rcciitapp.navigation.Destination
import com.example.rcciitapp.navigation.Navigation
import com.example.rcciitapp.ui.bottomNavBar.BottomNavBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val bottomBarHeight = 48.dp
    val bottomBarHeightPx = with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }

    /*val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.value + delta
                bottomBarOffsetHeightPx.value =
                    newOffset.coerceIn(-bottomBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }*/

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scaffoldState = rememberScrollState()
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
        topBar = {
            // TODO Hiding App Bar takes time. Should be fast
            if (drawerState.isClosed) {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
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
                        IconButton(onClick = {
                            /*if (drawerState.isClosed) {*/
                            coroutineScope.launch { drawerState.open() }
                            /* } else {
                                 coroutineScope.launch { drawerState.close() }
                             }*/
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = stringResource(id = R.string.cd_open_menu)
                            )
                        }
                    })
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                /*.height(bottomBarHeight)
                .offset { IntOffset(x = 0, y = -bottomBarOffsetHeightPx.value.roundToInt()) },*/
            ) {
                BottomNavBar(
                    currentDestination = currentDestination,
                    onNavigate = {
                        navController.navigate(it.path) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        },
        /*drawerContent = {
        // Migrated to material3. hence, have to move drawer
            //TODO pop backstack
            DrawerContent(
                modifier = Modifier.fillMaxWidth(),
                onNavigate = {
                    navController.navigate(it.path)
                    coroutineScope.launch { drawerState.close() }
                },
            )
        },*/

    ) { innerPadding ->
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet() {
                    DrawerContent(
                        modifier = Modifier.fillMaxWidth(),
                        onNavigate = {
                            navController.navigate(it.path)
                            coroutineScope.launch { drawerState.close() }
                        },
                    )
                }
            },
            scrimColor = scrimColor
        ) {
            Box(
                modifier = Modifier.padding(
                    0.dp,
                    60.dp,
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
            ) {
                Navigation(modifier = Modifier, navController = navController)

            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
