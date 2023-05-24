package com.example.rcciitapp.ui.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.R
import com.example.rcciitapp.ui.rccHome.ImageSlider
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun GalleryScreen() {
    val images = remember {
        mutableStateListOf(
            R.drawable.gallery_1, R.drawable.gallery_2, R.drawable.gallery_3
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        ImageSlider()
        /*
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.gallery_1), contentDescription = null
            )
            Spacer(modifier = Modifier.height(12.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.gallery_2), contentDescription = null
            )
            Spacer(modifier = Modifier.height(12.dp))
            *//*Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.gallery_), contentDescription = null, contentScale = ContentScale.FillBounds
        )*//*
        Spacer(modifier = Modifier.height(12.dp))*/

    }
}