package com.example.videogamesbrowser.data.datasource

import com.example.videogamesbrowser.data.remote.model.GameDetailsResponse
import com.example.videogamesbrowser.data.remote.model.GameResponse

interface MainDataSource {
    suspend fun getGames(
        genre: String,
        page: Int,
        pageSize: Int
    ): GameResponse

    suspend fun getGameDetails(
        id: Int
    ): GameDetailsResponse
}