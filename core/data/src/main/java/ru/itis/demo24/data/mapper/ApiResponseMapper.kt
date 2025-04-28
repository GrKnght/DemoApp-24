package ru.itis.demo24.data.mapper

import ru.itis.demo24.domain.model.SearchResultModel
import ru.itis.demo24.domain.model.SongDataModel
import ru.itis.demoapp24.core.network.pojo.response.SearchData
import ru.itis.demoapp24.core.network.pojo.response.SearchResult
import ru.itis.demoapp24.core.network.pojo.response.SongData
import ru.itis.demoapp24.core.network.pojo.response.SongDataResponse
import javax.inject.Inject

class ApiResponseMapper @Inject constructor() {

    fun mapToSongDataModel(input: SongDataResponse?): SongDataModel {
        return input?.let {
            SongDataModel(
                title = it.response?.song?.title ?: ""
            )
        } ?: SongDataModel.EMPTY
    }

    fun mapToSearchResultModelList(input: SongDataResponse?): List<SearchResultModel>? {
        return input?.let {
            it.response?.hits?.map { searchData ->
                mapSearchDataToModel(input = searchData.result)
            }
        }
    }

    private fun mapSearchDataToModel(input: SearchResult?): SearchResultModel {
        return input?.let {
            SearchResultModel(
                id = it.songId ?: 0,
                title = it.songTitle.orEmpty(),
                artist = it.artistName.orEmpty(),
                thumbnailUrl = it.songArtThumbnail.orEmpty(),
            )
        } ?: SearchResultModel.EMPTY
    }
}