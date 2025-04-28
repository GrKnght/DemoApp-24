package ru.itis.demo24.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.demo24.domain.di.qualifiers.IoDispatchers
import ru.itis.demo24.domain.model.DogFactsModel
import ru.itis.demo24.domain.repositories.DogsFactsRepository
import javax.inject.Inject
import kotlin.random.Random

class GetRandomFactUseCase @Inject constructor(
    @IoDispatchers private val coroutineDispatcher: CoroutineDispatcher,
    private val dogsFactsRepository: DogsFactsRepository,
) {

    suspend operator fun invoke(): DogFactsModel {
        return withContext(coroutineDispatcher) {
            val data = Random.nextInt(0, 5)
            DogFactsModel(
                header = "Header: $data",
                descr = "descr: $data",
            )
            // dogsFactsRepository.getRandomFacts()
        }
    }
}