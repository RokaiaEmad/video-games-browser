package com.example.videogamesbrowser.domain.usecase


import androidx.paging.PagingData
import com.example.videogamesbrowser.data.remote.model.Game
import com.example.videogamesbrowser.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(): Flow<PagingData<Game>> {
        return gameRepository.getGames()
    }
}