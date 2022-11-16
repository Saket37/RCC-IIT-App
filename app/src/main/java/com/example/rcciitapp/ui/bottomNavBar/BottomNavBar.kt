package com.example.rcciitapp.ui.bottomNavBar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.navigation.Destination
import java.util.*

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
) {
    NavigationBar(modifier = modifier) {
        listOf(
            Destination.RCC,
            Destination.Notice,
            Destination.Gallery,
            Destination.Faculty
        ).forEach {
            val selectedTab = currentDestination.path == it.path
            NavigationBarItem(
                selected = selectedTab,
                onClick = { onNavigate(it) },
                icon = {
                    if (selectedTab) {
                        it.selectedIcon?.let { image ->
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = image),
                                contentDescription = it.path
                            )
                        }
                    } else {
                        it.icon?.let { image ->
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = image),
                                contentDescription = it.path
                            )
                        }
                    }
                },
                label = {
                    Text(
                        text = it.path.replaceFirstChar { char ->
                            char.titlecase(Locale.getDefault())
                        }
                    )
                })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomBarPreview() {
    BottomNavBar(currentDestination = Destination.RCC, onNavigate = {})
}