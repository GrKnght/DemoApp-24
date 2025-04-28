package ru.itis.demo24.domain.repositories

import ru.itis.demo24.domain.model.DogFactsModel

interface DogsFactsRepository {

    suspend fun getRandomFacts(): DogFactsModel
}