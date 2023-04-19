package com.example.marsphotos.network

// serializable makes translating between JSON and Kotlin possible
@Serializable
data class MarsPhoto (
    val id: String, val
    // specify the corresponding JSON attribute in the result
    @SerialName(value = "img_src")
    imgSrc: String
)