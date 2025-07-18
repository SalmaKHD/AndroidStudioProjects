package com.salmakhd.android.peaceofmind.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val username: String = "",
    val password: String = "",
    val phoneNumber: String = ""
)