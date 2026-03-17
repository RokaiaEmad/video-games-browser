package com.example.videogamesbrowser.data.datasource

import com.example.videogamesbrowser.data.remote.api.GamesApiService
import com.example.videogamesbrowser.data.remote.dto.GameDetailsResponseDto
import com.example.videogamesbrowser.data.remote.dto.GameResponseDto
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(val apiService: GamesApiService) : MainDataSource {
    override suspend fun getGames(genre: String, page: Int, pageSize: Int): GameResponseDto {
        return apiService.getGames(
            genre = genre,
            page = page,
            pageSize = pageSize
        )
    }

    override suspend fun getGameDetails(id: Int): GameDetailsResponseDto {
        return apiService.getGameDetails(id)
    }
}