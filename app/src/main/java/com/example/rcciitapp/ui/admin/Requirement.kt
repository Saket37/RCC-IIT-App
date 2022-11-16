package com.example.rcciitapp.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rcciitapp.R
import com.example.rcciitapp.data.remote.entity.PasswordRequirements

@Composable
fun Requirement(modifier: Modifier = Modifier, message: String, satisfied: Boolean) {
    val tint = if (satisfied) {
        MaterialTheme.colorScheme.primary
    } else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)

    val requirementStatus = if (satisfied) {
        stringResource(
            id = R.string.password_requirement_satisfied, message
        )
    } else {
        stringResource(
            id = R.string.password_requirement_needed, message
        )
    }

    Row(modifier = modifier
        .semantics(mergeDescendants = true) {
            text = AnnotatedString(requirementStatus)
        }
        .padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = modifier.size(12.dp),
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = tint
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            modifier = modifier.clearAndSetSemantics { },
            text = message,
            fontSize = 12.sp,
            color = tint
        )
    }
}

@Composable
fun PasswordRequirements(
    modifier: Modifier = Modifier,
    satisfiedRequirements: List<PasswordRequirements>
) {
    Column(modifier = Modifier) {
        PasswordRequirements.values().forEach { requirements ->
            Requirement(
                message = stringResource(id = requirements.label),
                satisfied = satisfiedRequirements.contains(requirements)
            )
        }
    }
}