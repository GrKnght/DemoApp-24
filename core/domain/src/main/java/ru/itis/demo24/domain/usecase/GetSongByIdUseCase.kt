package ru.itis.demo24.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.demo24.domain.di.qualifiers.IoDispatchers
import ru.itis.demo24.domain.model.SongDataModel
import ru.itis.demo24.domain.repositories.SongDataRepository
import javax.inject.Inject

class GetSongByIdUseCase @Inject constructor(
    private val songDataRepository: SongDataRepository,
    @IoDispatchers val ioDispatchers: CoroutineDispatcher,
) {

    suspend operator fun invoke(songId: String): SongDataModel {
        return withContext(ioDispatchers) {
            songDataRepository.getSongById(songId = songId)
        }
    }
}