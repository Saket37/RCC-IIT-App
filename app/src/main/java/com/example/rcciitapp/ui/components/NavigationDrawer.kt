package com.example.rcciitapp.ui.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rcciitapp.R
import com.example.rcciitapp.navigation.Destination
import com.example.rcciitapp.ui.admin.AdminAuthActivity

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    onNavigate: (destination: Destination) -> Unit,
    close: () -> Unit,
    isAdminLoggedIn: Boolean,
    logout: () -> Unit,
) {
    Column {
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
                } else {
                    logout()
                    Toast.makeText(context, "Successfully Logged Out", Toast.LENGTH_SHORT).show()

                    close()
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
        Text(
            text = "Academic",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(16.dp)
                .clickable { })
        Text(
            text = "Admission",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(16.dp)
                .clickable { })
        Text(
            text = "Placement",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(16.dp)
                .clickable { onNavigate(Destination.Placement) })

        Text(
            text = "Anti Ragging",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(16.dp)
                .clickable { })

        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Contact Us",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {onNavigate(Destination.ContactUs) })
        }

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


