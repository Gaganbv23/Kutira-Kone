package com.example.kutirakone.data.models

data class Request(
    val id: String = "",
    val fromUser: String = "",
    val toUser: String = "",
    val scrapId: String = "",
    val type: String = "", // "buy" or "swap"
    val status: String = "pending" // "pending", "accepted", "rejected"
)
