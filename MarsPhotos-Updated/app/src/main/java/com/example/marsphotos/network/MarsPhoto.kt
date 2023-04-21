package com.example.marsphotos.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// serializable makes translating between JSON and Kotlin possible
@Serializable
data class MarsPhoto (
    val id: String,
    // specify the corresponding JSON attribute in the result
    @SerialName(value = "img_src")
    val imgSrc: String
)