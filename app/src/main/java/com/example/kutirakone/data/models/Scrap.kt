package com.example.kutirakone.data.models

data class Scrap(
    val id: String = "",
    val title: String = "",
    val material: String = "",
    val color: String = "",
    val size: String = "",
    val imageUrl: String = "",
    val userId: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis()
)
