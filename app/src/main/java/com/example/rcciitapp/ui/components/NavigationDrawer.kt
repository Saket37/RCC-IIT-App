package com.example.rcciitapp.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.R
import com.example.rcciitapp.navigation.Destination
import com.example.rcciitapp.ui.admin.AdminAuthActivity

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    onNavigate: (destination: Destination) -> Unit,
    close: () -> Unit,
    isAdminLoggedIn: Boolean
) {
    Column(

    ) {
        val context = LocalContext.current
        // TODO - Add image, admin login and other tabs
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { close() }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close Drawer")
            }
        }

        DrawerHeader()
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = modifier
                .padding(horizontal = 36.dp),
            onClick = {
                if (!isAdminLoggedIn) {
                    context.startActivity(Intent(context, AdminAuthActivity::class.java))
                }
            }) {
            Image(
                painter = painterResource(id = R.drawable.ic_admin_login),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = if (isAdminLoggedIn) "Sign Out" else "Admin Login")
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
    /*Layout(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(16.dp),
        content = {

        }, measurePolicy = { measurables, constraints ->
            lateinit var headerMeasurable: Measurable
            lateinit var contentMeasurable: Measurable
            measurables.forEach { }
        }
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


