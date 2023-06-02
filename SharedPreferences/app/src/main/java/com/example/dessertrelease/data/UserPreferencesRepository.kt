class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    /*
    datastore.data -> contains all key-value pairs
     */
    val isLinearLayout: Flow<Boolean> = dataStore.data
        // handle potential exceptions: IOExceptions
        .catch { exception ->

            if (exception is IOException) {
                Log.e(TAG, "ERROR reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferneces ->
            /*
            why set a default value? because by the time we access this pair, data may not have been saved yet
             */
            preferneces[IS_LINEAR_LAYOUT] ?: true

        }
    suspend fun saveLayoutPreference(isLinearLayout: Boolean) {
        // used to edit the value of a key
        // this key is defined and stored when a value is set to it
        dataStore.edit { preferences ->
            preferences[IS_LINEAR_LAYOUT] = isLinearLayout


        }
    }
    private companion object {
        // define a key in the datastore
        val IS_LINEAR_LAYOUT = booleanPreferencesKey("is_linear_layout")

        // used for logging
        const val TAG = "UserPreferencesRepo"


    }
}