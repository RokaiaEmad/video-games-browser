package com.example.videogamesbrowser.data.repository

import com.example.videogamesbrowser.data.datasource.MainDataSource
import com.example.videogamesbrowser.domain.model.response.Game
import com.example.videogamesbrowser.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
) : GameRepository {

    override suspend fun getGames(
        genre: String,
        page: Int,
        pageSize: Int
    ): List<Game> {
        return mainDataSource.getGames(
            genre = genre,
            page = page,
            pageSize = pageSize
        ).results
    }
}