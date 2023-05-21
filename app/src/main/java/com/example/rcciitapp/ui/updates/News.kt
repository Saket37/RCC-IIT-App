package com.example.rcciitapp.ui.updates

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.R

@Composable
fun News() {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            NewsCard(
                date = "12th September, 2022",
                description = "UG Electrical Engg Program has been accredited by NBA from AY 2022-2023 to 2024-2025 (Up to 30.06.2025)"
            )
            Spacer(modifier = Modifier.height(12.dp))
            NewsCard(
                date = "12th September, 2022",
                description = "UG Comp. Sc. & Engg Program has been accredited by NBA from AY 2022-2023 to 2024-2025 (Up to 30.06.2025)"
            )
            Spacer(modifier = Modifier.height(12.dp))
            NewsCard(
                date = "12th September, 2022",
                description = "UG Information Technology Program has been accredited by NBA from AY 2022-2023 to 2024-2025 (Up to 30.06.2025)"
            )
            Spacer(modifier = Modifier.height(12.dp))
            NewsCard(
                date = "12th September, 2022",
                description = "UG Electronics & Comm. Engg Program has been accredited by NBA from AY 2022-2023 to 2024-2025 (Up to 30.06.2025)"
            )
            Spacer(modifier = Modifier.height(12.dp))
            // WebViewWrapper(url = "https://www.rcciit.org/updates/news.aspx")
        }
    }
}

@Composable
fun NewsCard(date: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_news), contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = date, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = description)

            }
        }

    }
}