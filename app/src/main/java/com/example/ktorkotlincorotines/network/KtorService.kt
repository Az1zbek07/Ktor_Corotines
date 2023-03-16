package com.example.ktorkotlincorotines.network

import com.example.ktorkotlincorotines.model.User
import io.ktor.client.request.*

object KtorService {
    suspend fun getAllUsers(): List<User>{
        return KtorClient.httpClient.use {
            it.get("https://random-data-api.com/api/v2/users?size=10&is_json=true")
        }
    }

    suspend fun getOneUser(id: Int): User{
        return KtorClient.httpClient.use {
            it.get("https://random-data-api.com/api/v2/users?size=1&is_json=true")
        }
    }
}