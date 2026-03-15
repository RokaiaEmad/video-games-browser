package com.example.videogamesbrowser.domain.usecase


import com.example.videogamesbrowser.domain.model.response.Game
import com.example.videogamesbrowser.domain.repository.GameRepository
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        genre: String = "action",
        pageSize: Int = 6
    ): List<Game> {
        return gameRepository.getGames(
            genre = genre,
            page = page,
            pageSize = pageSize
        )
    }
}