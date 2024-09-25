package com.ebc.allaroundcakemakerapp.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppBoardingDataStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("appBoarding")
        val APP_BOARD = booleanPreferencesKey("app_board")
    }

    val getBoarding: Flow<Boolean> = context.dataStore.data
        .map {
            it[APP_BOARD] ?: false
        }

    suspend fun saveBoarding(value: Boolean) {
        context.dataStore.edit {
            it[APP_BOARD] = value
        }
    }
}