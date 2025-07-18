package com.salmakhd.android.peaceofmind.db

import androidx.room.Dao
import androidx.room.Query
import com.salmakhd.android.peaceofmind.model.Session
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Query("SELECT * FROM session")
    fun getSessions(): Flow<List<Session>>
}