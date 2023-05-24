package com.example.rcciitapp.ui.placement

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rcciitapp.R
import com.example.rcciitapp.ui.components.ChildAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacementScreen(navController: NavHostController,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    Scaffold(topBar = {
        ChildAppBar(title = "Placement") {
            navController.popBackStack()
        }
    }) { innerPadding ->
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
            LazyColumn {
                item {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        painter = painterResource(
                            id = R.drawable.placement1
                        ),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        painter = painterResource(
                            id = R.drawable.placement2
                        ),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        painter = painterResource(
                            id = R.drawable.placement3
                        ),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        painter = painterResource(
                            id = R.drawable.placement4
                        ),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        painter = painterResource(
                            id = R.drawable.placement5
                        ),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        painter = painterResource(
                            id = R.drawable.placement6
                        ),
                        contentDescription = null
                    )
                }
            }

        }
    }
}
