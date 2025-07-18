package com.salmakhd.android.peaceofmind.db

import com.salmakhd.android.peaceofmind.model.Session

@Dao
class SessionDao {
    @Query("SELECT * FROM session")
    fun getSessions(): Flow<List<Session>>
}