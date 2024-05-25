package com.example.recipeapp3.datastore

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.recipeapp3.ui.theme.White
import kotlinx.coroutines.flow.map


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager (val context: Context) {
    suspend fun saveSettings(settingsData: SettingsData) {
        context.dataStore.edit { preferences ->
            preferences[intPreferencesKey("text_size")] = settingsData.textSize
            preferences[longPreferencesKey("bg_color")] = settingsData.bgColor
        }
    }
    fun getSettings() = context.dataStore.data.map { preferences ->
        return@map SettingsData(
            preferences[intPreferencesKey("text_size")] ?: 26,
            preferences[longPreferencesKey("bg_color")] ?: White.value.toLong()
        )

    }


}