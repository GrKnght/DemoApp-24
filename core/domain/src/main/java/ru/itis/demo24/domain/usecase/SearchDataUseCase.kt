package ru.itis.demo24.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.demo24.domain.di.qualifiers.IoDispatchers
import ru.itis.demo24.domain.model.SearchResultModel
import ru.itis.demo24.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchDataUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    @IoDispatchers private val ioDispatchers: CoroutineDispatcher,
) {

    suspend operator fun invoke(query: String): List<SearchResultModel> {
        return withContext(ioDispatchers) {
            searchRepository.getSongsDataByQuery(query = query)
        }
    }
}