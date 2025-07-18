package com.salmakhd.android.peaceofmind.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salmakhd.android.peaceofmind.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): Flow<User>
}