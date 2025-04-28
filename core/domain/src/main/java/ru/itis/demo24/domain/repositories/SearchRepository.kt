package ru.itis.demo24.domain.repositories

import ru.itis.demo24.domain.model.SearchResultModel

interface SearchRepository {

    suspend fun getSongsDataByQuery(query: String): List<SearchResultModel>
}