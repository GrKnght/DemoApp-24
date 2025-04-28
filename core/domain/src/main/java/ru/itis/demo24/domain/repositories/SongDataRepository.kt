package ru.itis.demo24.domain.repositories

import ru.itis.demo24.domain.model.SongDataModel

interface SongDataRepository {

    suspend fun getSongById(songId: String): SongDataModel
}