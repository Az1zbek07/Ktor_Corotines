package com.example.ktorkotlincorotines.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json

object KtorClient {
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }
    val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("@@@Ktor", "log: $message")
                }
            }
            level = LogLevel.ALL
        }
        install(HttpTimeout) {
            socketTimeoutMillis = 3000
            requestTimeoutMillis = 3000
            connectTimeoutMillis = 3000
        }
    }
}