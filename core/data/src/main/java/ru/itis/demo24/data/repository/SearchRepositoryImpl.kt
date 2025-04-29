package ru.itis.demo24.data.repository

import ru.itis.demo24.data.mapper.ApiResponseMapper
import ru.itis.demo24.domain.model.SearchResultModel
import ru.itis.demo24.domain.repositories.SearchRepository
import ru.itis.demoapp24.core.network.GeniusApi
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val geniusApi: GeniusApi,
    private val mapper: ApiResponseMapper,
) : SearchRepository {

    override suspend fun getSongsDataByQuery(query: String): List<SearchResultModel> {
        val modelsList = geniusApi.searchByQuery(query = query).let(mapper::mapToSearchResultModelList)
        return modelsList?.filter { it.id != -1 } ?: emptyList()
    }
}