package com.example.rcciitapp.ui.rccHome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(modifier: Modifier = Modifier) {
    Column {
        val imageList = listOf(
            R.drawable.ic_rcc_1,
            R.drawable.gallery_1,
            R.drawable.gallery_4,
            R.drawable.ic_rcc_2,
            R.drawable.ic_rcc_3,
            R.drawable.ic_rcc_4,
            R.drawable.ic_rcc_5,
            R.drawable.gallery_2
        )
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        HorizontalPager(count = imageList.size, state = pagerState) { page ->
            Column() {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    painter = painterResource(id = imageList[page]),
                    contentDescription = null
                )
            }

        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            activeColor = MaterialTheme.colorScheme.secondary,
            inactiveColor = MaterialTheme.colorScheme.onSecondaryContainer
        )

        LaunchedEffect(key1 = pagerState.currentPage) {
            coroutineScope.launch {
                delay(3000)
                var newPosition = pagerState.currentPage + 1
                if (newPosition > imageList.size - 1) newPosition = 0
                // scrolling to the new position.
                pagerState.animateScrollToPage(newPosition)
            }


        }
    }
}