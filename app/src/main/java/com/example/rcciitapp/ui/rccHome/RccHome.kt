package com.example.rcciitapp.ui.rccHome

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rcciitapp.R


@Composable
fun RccHome(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val bullet = listOf(
        context.getString(R.string.mission_1),
        context.getString(R.string.mission_2),
        context.getString(R.string.mission_3),
        context.getString(R.string.mission_4),
        context.getString(R.string.mission_5)
    )
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        item {
            AboutRccTitle(title = R.string.about_rcc_title)
            AboutRCCParagraph(
                modifier = Modifier.padding(horizontal = 16.dp),
                para1 = R.string.about_rcc_para1,
                para2 = R.string.about_rcc_para2
            )
            Spacer(modifier = Modifier.height(24.dp))
            AboutRccTitle(title = R.string.rcc_vision_title)
            AboutRCCParagraph(
                modifier = Modifier.padding(horizontal = 16.dp),
                para1 = R.string.rcc_vision,
                para2 = null
            )

            Spacer(modifier = Modifier.height(24.dp))
            AboutRccTitle(title = R.string.rcc_mission_title)
        }

        items(bullet) {
            Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Canvas(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(10.dp)
                ) {
                    drawCircle(Color.White)
                }
                Text(text = it, fontSize = 16.sp)

            }
        }
    }
}