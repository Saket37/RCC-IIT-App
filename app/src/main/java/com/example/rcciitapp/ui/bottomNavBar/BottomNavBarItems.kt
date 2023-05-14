package com.example.rcciitapp.ui.bottomNavBar

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.rcciitapp.navigation.Destination
import java.util.*


class BottomNavBarItems(
    val selected: Boolean,
    val onCLick: () -> Unit,
    val icon: @Composable () -> Unit,
    val label: @Composable () -> Unit
)

fun buildNavigationBarItems(
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
): List<BottomNavBarItems> {
    return listOf(
        Destination.RCC,
        Destination.Update,
        Destination.Gallery,
        Destination.Courses
    ).map {
        BottomNavBarItems(
            label = {
                Text(
                    text = it.path.replaceFirstChar { char ->
                        char.titlecase(Locale.getDefault())
                    }
                )
            },
            icon = {
                it.icon?.let { image ->
                    Icon(painter = painterResource(id = image), contentDescription = null)
                }
            }, selected = currentDestination.path == it.path,
            onCLick = { onNavigate(it) }
        )
    }
}