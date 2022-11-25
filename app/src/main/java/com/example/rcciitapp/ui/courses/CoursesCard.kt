package com.example.rcciitapp.ui.courses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.ui.theme.Blue52

@Composable
fun CoursesCard(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    title: String,
    branch: String,
    duration: String,
    intake: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigate() }
            .padding(top = 6.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
        colors = CardDefaults.cardColors(Blue52), elevation = CardDefaults.cardElevation(6.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title, textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Branch - $branch")
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Duration - $duration",
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(text = "Intake - $intake")

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
