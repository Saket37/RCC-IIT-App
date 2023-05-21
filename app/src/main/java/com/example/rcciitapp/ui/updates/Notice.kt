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
fun Notice() {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            NoticeCard(
                date = " 12th May, 2023",
                description = "THlS IS TO INFORM TO ALL THE STUDENTS OF B.TECH (CSE/IT) 1ST YEAR 2022-2023 BATCH THAT THEIR STUDENT’S IDENTITY CARDS WILL BE DISTRIBUTED FROM 15.05.2023 I.E. FROM MONDAY AT 11 A.M. ONWARDS. ALL THE STUDENTS OF B.TECH (CSE/IT) ARE HEREBY INFORMED TO COLLECT IT FROM EXAM- DEPARTMENT ROOM NO. N420D. BY ORDER"
            )
            Spacer(modifier = Modifier.height(12.dp))
            NoticeCard(
                date = " 17th April, 2023",
                description = "Considering the notice No. 356-Edn(U) dated 16.04.2023 given by the Department of Higher Education, Govt. of West Bengal, all the students are hereby informed that only during the period of closure of the Institute for heat wave, the Library late fine will not be counted. However, fine will be counted as usual from the date when the normal classes will be resumed."
            )
            //WebViewWrapper(url = "https://www.rcciit.org/updates/notice.aspx")
        }
    }
}

@Composable
fun NoticeCard(date: String, description: String) {
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
                painter = painterResource(id = R.drawable.ic_notice), contentDescription = null
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