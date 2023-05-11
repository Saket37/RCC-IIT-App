package com.example.rcciitapp.ui.courses

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rcciitapp.R
import com.example.rcciitapp.model.Faculty

@Composable
fun FacultyCard(
    modifier: Modifier = Modifier,
    faculty: Faculty
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FacultyProfileImage(modifier = Modifier.padding(6.dp), image = faculty.image)
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            FacultyDetail(
                name = faculty.name,
                degree = faculty.degree,
                designation = faculty.designation,
                doj = faculty.doj,
                email = faculty.email
            )
        }

    }
}

@Composable
fun FacultyDetail(
    modifier: Modifier = Modifier,
    name: String,
    degree: String,
    designation: String,
    doj: String,
    email: String
) {
    Column(
        modifier = Modifier.padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row() {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = degree, fontSize = 12.sp)
        }
        Text(text = designation, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(6.dp))
        Text(fontSize = 12.sp, text = buildAnnotatedString {
            append(stringResource(R.string.doj))
            append(" ")
            append(doj)
        })
        Spacer(modifier = Modifier.height(6.dp))
        Text(fontSize = 12.sp, text = buildAnnotatedString {
            append(stringResource(id = R.string.email))
            append(" ")
            append(email)
        })
    }
}

@Composable
fun FacultyProfileImage(
    image: Int, modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(60.dp)
            .clip(CircleShape),
        painter = painterResource(id = image),
        contentDescription = null,
    )
}

@Preview
@Composable
fun FacultyProfileImagePreview() {
    FacultyProfileImage(image = R.drawable.cse_minakshi)
}

@Preview(showBackground = true)
@Composable
fun FacultyCardPreview() {
    FacultyCard(
        faculty = Faculty(
            id = "CSE_1",
            name = "Dr. Minakshi Banerjee",
            degree = "PhD (Engg.)",
            doj = "01.09.2008",
            designation = "Professor",
            email = "minakshi.banerjee@rcciit.org.in",
            image = R.drawable.cse_minakshi
        )
    )
}