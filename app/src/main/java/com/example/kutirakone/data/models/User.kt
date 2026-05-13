package com.example.kutirakone.data.models

data class User(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
