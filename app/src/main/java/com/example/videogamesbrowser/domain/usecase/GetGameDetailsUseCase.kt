package com.example.videogamesbrowser.domain.usecase

import com.example.videogamesbrowser.data.remote.model.GameDetailsResponse
import com.example.videogamesbrowser.domain.repository.GameRepository
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(
        id: Int
    ): GameDetailsResponse {
        return gameRepository.getGameDetails(id)
    }
}
