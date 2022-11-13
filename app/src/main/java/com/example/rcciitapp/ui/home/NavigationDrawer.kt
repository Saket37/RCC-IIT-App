package com.example.rcciitapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.R

@Composable
fun ColumnScope.DrawerContent(modifier: Modifier = Modifier) {
    // TODO - Add image, admin login and other tabs
    DrawerHeader()
    Spacer(modifier = Modifier.height(8.dp))
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
            .padding(top = 24.dp, start = 24.dp,end = 24.dp), contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            painter = painterResource(id = R.drawable.ic_drawer_image),
            contentDescription = null
        )
    }
}


