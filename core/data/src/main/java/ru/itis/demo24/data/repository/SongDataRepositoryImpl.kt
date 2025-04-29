package ru.itis.demo24.data.repository

import ru.itis.demo24.data.mapper.ApiResponseMapper
import ru.itis.demo24.domain.model.SongDataModel
import ru.itis.demo24.domain.repositories.SongDataRepository
import ru.itis.demoapp24.core.network.GeniusApi
import javax.inject.Inject

class SongDataRepositoryImpl @Inject constructor(
    private val geniusApi: GeniusApi,
    private val responseMapper: ApiResponseMapper,
) : SongDataRepository {

    override suspend fun getSongById(songId: String): SongDataModel {
        return geniusApi.getSongById(songId = songId).let(responseMapper::mapToSongDataModel)
    }
}