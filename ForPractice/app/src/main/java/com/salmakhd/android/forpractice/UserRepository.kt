package com.salmakhd.android.forpractice

import kotlinx.coroutines.delay

// simulates a repository that gets data from a data source that uses coroutines
class UserRepository {
    suspend fun getUsers(): List<User> {
       // simulate long-running operation
        delay(8000)
        return listOf(
            User(129, "Salma"),
            User(134, "Soheil")
        )
    }
}