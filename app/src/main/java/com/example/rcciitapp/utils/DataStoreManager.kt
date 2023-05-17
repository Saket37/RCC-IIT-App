package com.example.rcciitapp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_data")
        private val USER_TOKEN_KEY = stringPreferencesKey("user_token")
        private val USER_EMAIL = stringPreferencesKey("user_email")
        private val USER_NAME = stringPreferencesKey("user_name")
        private val USER_ID = stringPreferencesKey("user_id")
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { pref ->
            pref[USER_TOKEN_KEY]
        }
    }

    fun getUserName(): Flow<String?> {
        return context.dataStore.data.map { pref ->
            pref[USER_NAME]
        }
    }

    suspend fun saveUserData(token: String, email: String, name: String, id: String) {
        context.dataStore.edit { pref ->
            pref[USER_TOKEN_KEY] = token
            pref[USER_EMAIL] = email
            pref[USER_NAME] = name
            pref[USER_ID] = id
        }
    }

    fun isAdminLoggedIn(): Flow<Boolean> {
        return getToken().map { token ->
            !token.isNullOrEmpty()
        }
    }
}