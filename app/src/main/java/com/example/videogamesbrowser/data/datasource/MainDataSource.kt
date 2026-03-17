package com.example.videogamesbrowser.data.datasource

import com.example.videogamesbrowser.data.remote.dto.GameDetailsResponseDto
import com.example.videogamesbrowser.data.remote.dto.GameResponseDto

interface MainDataSource {
    suspend fun getGames(
        genre: String,
        page: Int,
        pageSize: Int
    ): GameResponseDto

    suspend fun getGameDetails(
        id: Int
    ): GameDetailsResponseDto
}