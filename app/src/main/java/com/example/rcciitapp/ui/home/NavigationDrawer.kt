package com.example.rcciitapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.R
import com.example.rcciitapp.navigation.Destination

@Composable
fun ColumnScope.DrawerContent(
    modifier: Modifier = Modifier,
    onNavigate: (destination: Destination) -> Unit,
) {
    // TODO - Add image, admin login and other tabs
    DrawerHeader()
    Spacer(modifier = Modifier.height(12.dp))
    Button(
        modifier = modifier
            .align(alignment = Alignment.CenterHorizontally)
            .padding(horizontal = 36.dp),
        onClick = { onNavigate(Destination.AdminLogin) }) {
        Image(painter = painterResource(id = R.drawable.ic_admin_login), contentDescription = null)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = "Admin Login")
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )


    /*GlideImage(
        model = R.drawable.ic_drawer_image,
        contentDescription = null,
        modifier = modifier.size(50.dp)
    )*/

}

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp), contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            painter = painterResource(id = R.drawable.ic_drawer_image),
            contentDescription = null
        )
    }
}


