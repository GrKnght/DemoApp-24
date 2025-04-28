package ru.itis.demoapp24.core.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.itis.demoapp24.core.network.pojo.response.SongDataResponse

interface GeniusApi {

    @GET("/search")
    suspend fun searchByQuery(
        @Query("q") query: String,
    ): SongDataResponse?

    @GET("/songs/{song_id}")
    suspend fun getSongById(
        @Path("song_id") songId: String
    ): SongDataResponse?
}