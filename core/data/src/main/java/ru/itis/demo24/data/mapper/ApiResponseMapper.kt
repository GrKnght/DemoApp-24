package ru.itis.demo24.data.mapper

import ru.itis.demo24.domain.model.SongDataModel
import ru.itis.demoapp24.core.network.pojo.response.SongDataResponse
import javax.inject.Inject

class ApiResponseMapper @Inject constructor() {

    fun map(input: SongDataResponse?): SongDataModel {
        return input?.let {
            SongDataModel(
                title = it.response?.song?.title ?: ""
            )
        } ?: SongDataModel.EMPTY
    }

}