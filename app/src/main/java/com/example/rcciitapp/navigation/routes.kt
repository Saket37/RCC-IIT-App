package com.example.rcciitapp.navigation

import android.util.Log
import com.example.rcciitapp.R

sealed class Destination(
    val path: String,
    val icon: Int? = null,
    val selectedIcon: Int? = null,
    val isRootDestination: Boolean = true,
) {
    companion object {
        fun fromString(route: String): Destination {
            return when (route) {
                RCC.path -> RCC
                Courses.path -> Courses
                Gallery.path -> Gallery
                Update.path -> Update

                else -> Home
            }
        }
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
                Log.d("COURSE_DETAILS", arg)
            }
        }
    }

    fun args(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    object Home : Destination("rccHome", isRootDestination = true)
    object RCC : Destination(
        path = "home",
        icon = R.drawable.ic_outline_home,
        selectedIcon = R.drawable.ic_filled_home,
        isRootDestination = true
    )

    object Courses : Destination(
        path = "courses",
        icon = R.drawable.ic_outline_faculty,
        selectedIcon = R.drawable.ic_filled_faculty,
        isRootDestination = true
    )

    object Gallery : Destination(
        path = "gallery",
        icon = R.drawable.ic_outline_gallery,
        selectedIcon = R.drawable.ic_filled_gallery,
        isRootDestination = true
    )

    object Update : Destination(
        path = "updates",
        icon = R.drawable.ic_outline_notice,
        selectedIcon = R.drawable.ic_filled_notice,
        isRootDestination = true
    )

    object Faculty : Destination(path = "course", isRootDestination = false)
    object AddFaculty : Destination(path = "addFaculty", isRootDestination = false)
    object EditFaculty : Destination(path = "editFaculty", isRootDestination = false)
    object Placement : Destination(path = "placement", isRootDestination = false)
    object ContactUs : Destination(path = "contactUs", isRootDestination = false)
    //object AdminLogin : Destination(path = "adminLogin", isRootDestination = false)
}