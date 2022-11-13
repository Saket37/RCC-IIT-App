package com.example.rcciitapp.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val path: String,
    val icon: ImageVector? = null,
    val isRootDestination: Boolean = true,
) {
    companion object {
        fun fromString(route: String): Destination {
            return when (route) {
                RCC.path -> RCC
                Faculty.path -> Faculty
                Gallery.path -> Gallery
                Notice.path -> Notice
                else -> Home
            }
        }
    }

    object Home : Destination("home")
    object RCC : Destination("rcc")
    object Faculty : Destination("faculty")
    object Gallery : Destination("gallery")
    object Notice : Destination("notice")
}