package com.artemissoftware.multimovies.data.network


import io.github.aakira.napier.Napier
import io.github.aakira.napier.log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

internal abstract class MovieApi {

    val client = HttpClient {

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.d(tag = "TAG_KTOR_LOGGER", message = message )
                }
            }
            level = LogLevel.ALL
        }

        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }


    fun HttpRequestBuilder.pathUrl(path: String){
        url {
            takeFrom(BASE_URL)
            path("3", path)
            parameter("api_key", API_KEY)
        }
    }

    companion object{
        private const val BASE_URL = "https://api.themoviedb.org/"
        private const val API_KEY = "YOUR_API_KEY"
    }
}