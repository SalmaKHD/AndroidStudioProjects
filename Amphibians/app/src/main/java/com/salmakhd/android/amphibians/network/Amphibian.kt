package com.salmakhd.android.amphibians.network

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Amphibian (
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)