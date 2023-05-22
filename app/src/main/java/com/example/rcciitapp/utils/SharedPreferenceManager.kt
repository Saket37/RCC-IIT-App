package com.example.rcciitapp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager(context: Context) {
    companion object {
        private const val PREFERENCES_NAME = "user_data"
        private const val USER_TOKEN_KEY = "user_token"
        private const val USER_EMAIL = "user_email"
        private const val USER_NAME = "user_name"
        private const val USER_ID = "user_id"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun getToken(): String? {
        return preferences.getString(USER_TOKEN_KEY, null)
    }

    fun getUserName(): String? {
        return preferences.getString(USER_NAME, null)
    }

    fun saveUserData(token: String, email: String, name: String, id: String) {
        preferences.edit().apply {
            putString(USER_TOKEN_KEY, token)
            putString(USER_EMAIL, email)
            putString(USER_NAME, name)
            putString(USER_ID, id)
            apply()
        }
    }

    fun deleteUserData() {
        preferences.edit().apply {
            remove(USER_TOKEN_KEY)
            remove(USER_EMAIL)
            remove(USER_NAME)
            remove(USER_ID)
            apply()
        }
    }

    fun isAdminLoggedIn(): Boolean {
        return preferences.contains(USER_TOKEN_KEY)
    }
}
