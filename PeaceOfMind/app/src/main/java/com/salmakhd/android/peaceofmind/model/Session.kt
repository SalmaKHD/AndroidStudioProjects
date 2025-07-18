package com.salmakhd.android.peaceofmind.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data class Session (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val name: String = "",
    val imageId : Int = -1,
    val description: String = "",
    val practiceDescription: String = "",
    val isDone: Boolean = false
)