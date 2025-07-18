package com.salmakhd.android.peaceofmind.model;

@Entity(tableName = "user")
data class User(
        @PrimaryKey(autoGenerate = true)
                val id: Int = 0,
        val name: String,
        val username: String,
        val password: String,
        val phoneNumber: String
)