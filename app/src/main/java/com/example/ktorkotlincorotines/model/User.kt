package com.example.ktorkotlincorotines.model

@kotlinx.serialization.Serializable
data class User(
    val address: Address,
    val avatar: String,
    val credit_card: CreditCard,
    val date_of_birth: String,
    val email: String,
    val employment: Employment,
    val first_name: String,
    val gender: String,
    val id: Int,
    val last_name: String,
    val password: String,
    val phone_number: String,
    val social_insurance_number: String,
    val subscription: Subscription,
    val uid: String,
    val username: String
)