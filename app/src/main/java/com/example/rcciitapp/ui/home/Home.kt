package com.example.rcciitapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.material3.DrawerDefaults.scrimColor
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rcciitapp.navigation.Destination
import com.example.rcciitapp.navigation.Navigation
import com.example.rcciitapp.ui.components.BottomNavBar
import com.example.rcciitapp.ui.components.ConnectivityStatus
import com.example.rcciitapp.ui.components.DrawerContent
import com.example.rcciitapp.ui.components.RccTopAppBar
import com.example.rcciitapp.ui.theme.RCCIITAppTheme
import com.example.rcciitapp.utils.LogoutEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RccApp(
    isConnected: Boolean,
    isAdminLoggedIn: Boolean,
    handleEvent: (event: LogoutEvent) -> Unit,
) {
    RCCIITAppTheme {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        val navController = rememberNavController()

        val coroutineScope = rememberCoroutineScope()
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
                        close = { coroutineScope.launch { drawerState.close() } },
                        isAdminLoggedIn = isAdminLoggedIn,
                        logout = {
                            handleEvent(LogoutEvent.Logout)
                        }
                    )
                }
            }, scrimColor = scrimColor, gesturesEnabled = false
        ) {
            HomeScreen(
                navController = navController,
                openDrawer = { coroutineScope.launch { drawerState.open() } },
                isConnected = isConnected,
                isAdminLoggedIn = isAdminLoggedIn
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit,
    isConnected: Boolean,
    isAdminLoggedIn: Boolean
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val bottomBarHeight = 86.dp
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

    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)


    val scaffoldState = rememberScrollState()

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

    Scaffold(
        topBar = {
            when (currentDestination) {
                Destination.RCC, Destination.Courses, Destination.Gallery, Destination.Update -> {
                    RccTopAppBar(openDrawer = openDrawer, topAppBarState = topAppBarState)
                }
                else -> {}
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                /*.height(bottomBarHeight)
                .offset { IntOffset(x = 0, y = -bottomBarOffsetHeightPx.value.roundToInt()) },*/
            ) {
                BottomNavBar(currentDestination = currentDestination, onNavigate = {
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
            Column() {
                ConnectivityStatus(isConnected = isConnected)
                Navigation(modifier = Modifier, navController = navController)
            }

        }


    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(openDrawer = {}, isConnected = false, isAdminLoggedIn = false)
}

