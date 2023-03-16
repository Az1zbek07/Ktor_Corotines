package com.example.ktorkotlincorotines.model

@kotlinx.serialization.Serializable
data class Subscription(
    val payment_method: String,
    val plan: String,
    val status: String,
    val term: String
)