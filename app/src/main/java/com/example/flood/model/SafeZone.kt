package com.example.flood.model

import kotlinx.serialization.Serializable

@Serializable
data class SafeZone(
    val id: Long,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val capacity: Int?,
    val contact: Long?
    )
