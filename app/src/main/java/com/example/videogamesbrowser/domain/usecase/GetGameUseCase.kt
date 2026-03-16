package com.example.videogamesbrowser.domain.usecase


import com.example.videogamesbrowser.data.remote.model.Game
import com.example.videogamesbrowser.domain.repository.GameRepository
import com.example.videogamesbrowser.utils.Constants
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        genre: String = Constants.DEFAULT_GENRE,
        pageSize: Int = Constants.PAGE_SIZE
    ): List<Game> {
        return gameRepository.getGames(
            genre = genre,
            page = page,
            pageSize = pageSize
        )
    }
}