package com.example.rcciitapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rcciitapp.ui.components.ChildAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsScreen(
    navController: NavHostController,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    Scaffold(topBar = {
        ChildAppBar(title = "Contact Us") {
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
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                /*Text(
                    text = "RCC Institute of Information Technology",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = " Approved by AICTE, New Delhi and Affiliated to MAKAUT, W.B.")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = " An ISO 9001 - 2008 & ISO 14001 - 2004 Certified Institute")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "A Unit of RCC Institute of Technology an autonomous Society of Department of Higher Education, Govt. of West Bengal")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Postal Address - Canal South Road, Beliaghata, Kolkata - 700 015, West Bengal, India")
                Spacer(modifier = Modifier.height(8.dp))*/
                Text(
                    "Principal - Email: ", fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(start = 48.dp),
                    text = "principal@rcciit.org.in",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Registrar - Email: ", fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(start = 48.dp),
                    text = "registrar@rcciit.org.in",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Admission & Examination - ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(start = 48.dp),
                    text = "+916290821297\\n\" + \"Email: admission@rcciit.org.in",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Finance & Accounts -",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(start = 48.dp),

                    text = " +916290821300\\n\" +\n" +
                            "\"Email: finance@rcciit.org.in",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

        }
    }
}