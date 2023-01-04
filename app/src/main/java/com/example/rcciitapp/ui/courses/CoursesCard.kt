package com.example.rcciitapp.ui.courses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CoursesCard(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    title: String,
    branch: String,
    duration: String,
    intake: String,
    size: Size
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 300.dp)
            .clickable { onNavigate() }
            .padding(top = 6.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            val density = LocalDensity.current.density
            var padding by remember { mutableStateOf(0.dp) }
            Text(
                text = title, textAlign = TextAlign.Center,
                onTextLayout = {
                    val lineCount = it.lineCount
                    val height = (it.size.height / density).dp
                    padding = if (lineCount > 1) 0.dp else height
                },
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Branch - $branch")
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Duration - $duration",
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(text = "Intake - $intake")
            println("Height: $size")
        }
    }
}

/*
@Preview
@Composable
fun CoursesCardPreview() {
    CoursesCard(modifier = Modifier.fillMaxWidth()) {

    }
}*/
