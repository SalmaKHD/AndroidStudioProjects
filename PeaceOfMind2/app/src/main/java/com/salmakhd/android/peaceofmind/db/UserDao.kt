package com.salmakhd.android.peaceofmind.db

import com.salmakhd.android.peaceofmind.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(user: User): Flow<User>
}