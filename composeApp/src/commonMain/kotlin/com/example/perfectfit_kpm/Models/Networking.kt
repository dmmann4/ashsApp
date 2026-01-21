package com.example.perfectfit_kpm.Models

import com.example.perfectfit_kpm.Exercise
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable


class Networking {
    val masterKey = "${'$'}2a${'$'}10${'$'}p0rPW/2I88B1ire0LLB8/uPK9pUGX6NmcyS3aqfMUKkCt8thQbykW"
    val accessKey = "${'$'}2a${'$'}10${'$'}yEVALU.GWTuIA96mZLJJJOr.konQT.0HE70xaTbhOVdM8FofIObJC"
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true // ignore unused JSON fields
                    isLenient = true
                }
            )
        }
    }
    suspend fun getExercises(): List<Exercise> {
        val response: ExerciseResponse = httpClient.get("https://api.jsonbin.io/v3/b/69711660ae596e708feba4a4"){
            headers {
                append("X-MASTER-KEY", masterKey)
                append("X-ACCESS-KEY", accessKey)
            }
        }.body()
        return response.record
    }
}

@Serializable
data class ExerciseResponse(
    val record: List<Exercise>
)