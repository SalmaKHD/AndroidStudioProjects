package com.salmakhd.android.peaceofmind.model

@Entity
data class Session (
    @PrimaryKey(autoGeenrate = true)
    val id: Int = 0,
    val title: String = "",
    val name: String = "",
    val imageId : Int = -1,
    val description: String = "",
    val practiceDescription: String = "",
    val isDone: Boolean = false
)