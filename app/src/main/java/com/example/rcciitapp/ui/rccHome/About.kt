package com.example.rcciitapp.ui.rccHome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutRccTitle(modifier: Modifier = Modifier, title: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clip(RectangleShape),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = stringResource(id = title), fontSize = 24.sp)
    }
}

@Composable
fun AboutRCCParagraph(modifier: Modifier = Modifier, para1: Int?, para2: Int?) {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(textAlign = TextAlign.Justify)) {
                if (para1 != null) {
                    append(stringResource(id = para1))
                }
            }
            withStyle(style = ParagraphStyle(textAlign = TextAlign.Justify)) {
                if (para2 != null) {
                    append(stringResource(id = para2))
                }
            }

        }, fontSize = 16.sp, modifier = modifier

    )
}

