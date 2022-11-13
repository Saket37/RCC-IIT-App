package com.example.rcciitapp.ui.bottomNavBar

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
        Destination.Notice,
        Destination.Gallery,
        Destination.Faculty
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
                    Icon(imageVector = image, contentDescription = null)
                }
            }, selected = currentDestination.path == it.path,
            onCLick = { onNavigate(it) }
        )
    }
}