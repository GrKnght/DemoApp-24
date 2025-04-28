package ru.itis.demo24.data.repository

import ru.itis.demo24.data.mapper.DogsApiResponseMapper
import ru.itis.demo24.data.remote.DogFactsApi
import ru.itis.demo24.domain.model.DogFactsModel
import ru.itis.demo24.domain.repositories.DogsFactsRepository
import javax.inject.Inject

class DogFactsRepositoryImpl @Inject constructor(
    private val dogsFactsApi: DogFactsApi,
    private val mapper: DogsApiResponseMapper,
) : DogsFactsRepository {

    override suspend fun getRandomFacts(): DogFactsModel {
        return dogsFactsApi.getRandomFact().let(mapper::map)
    }
}