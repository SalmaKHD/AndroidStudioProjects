package com.bignerdranch.android.photogallery

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

// This repo will enable the app to interact with shared preferences through DataStore library
class PreferencesRepository private constructor(
    // define a DataStore instance
    private val dataStore: DataStore<Preferences>
) {
    // store the values in key-value pairs in a Flow object and set a default string value
    val storedQuery: Flow<String> = dataStore.data.map {
        it[SEARCH_QUERY_KEY] ?: ""
    }.distinctUntilChanged()

    // define a function that will change the data stored in shared preferences
    // (needed for when the query entered into the SearchView changes)
    suspend fun setStoredQuery(query: String) {
        // .edit() method used for editing the data stored in shared preferences
        dataStore.edit {
            // set data to the new query
            it[SEARCH_QUERY_KEY] = query
        }
    }

    // fetch the id of the latest photo from file
    val lastResultId: Flow<String> = dataStore.data.map {
        it[PREF_LAST_RESULT_ID] ?: ""
    }.distinctUntilChanged()

    // save the latest photo id in a file to compare to check if new photos can be fetched
    suspend fun setLastResultId(lastResultId: String) {
        dataStore.edit {
            it[PREF_LAST_RESULT_ID] = lastResultId
        }
    }

    // determine if the worker is currently running
    val isPolling: Flow<Boolean> = dataStore.data.map {
        it[PREF_IS_POLLING] ?: false
    }.distinctUntilChanged()
    suspend fun setPolling(isPolling: Boolean) {
        dataStore.edit {
            it[PREF_IS_POLLING] = isPolling
        }
    }

    companion object {
        private val SEARCH_QUERY_KEY = stringPreferencesKey("search_query")
        private val PREF_LAST_RESULT_ID = stringPreferencesKey("lastResultId")
        private val PREF_IS_POLLING = booleanPreferencesKey("isPolling")
        private var INSTANCE: PreferencesRepository? = null

        // create a singleton
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                val dataStore = PreferenceDataStoreFactory.create {
                    // create a file in the filesystem and give it a name
                    context.preferencesDataStoreFile("settings")
                }
                INSTANCE = PreferencesRepository(dataStore)
            }
        }
        // get the instance, if null throw an exception (no calls to the initialize() fun)
        fun get(): PreferencesRepository {
            return INSTANCE ?: throw IllegalStateException(
                "PreferencesRepository must be initialized"
            )
        }
    }
}