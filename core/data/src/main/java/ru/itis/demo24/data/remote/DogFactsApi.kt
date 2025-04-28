package ru.itis.demo24.data.remote

import retrofit2.http.GET
import ru.itis.demo24.data.remote.response.DogsFactResponse

interface DogFactsApi {

    @GET("/")
    suspend fun getRandomFact(): DogsFactResponse?
}