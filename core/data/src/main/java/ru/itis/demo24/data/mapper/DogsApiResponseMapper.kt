package ru.itis.demo24.data.mapper

import ru.itis.demo24.data.remote.response.DogsFactResponse
import ru.itis.demo24.domain.model.DogFactsModel
import javax.inject.Inject

class DogsApiResponseMapper @Inject constructor() {

    fun map(input: DogsFactResponse?): DogFactsModel {
        return DogFactsModel(
            header = "header",
            descr = "text",
        )
    }
}