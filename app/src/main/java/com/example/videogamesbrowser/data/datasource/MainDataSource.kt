package com.example.videogamesbrowser.data.datasource

import com.example.videogamesbrowser.domain.model.response.GamesResponse

interface MainDataSource {
    suspend fun getGames(
        genre: String,
        page: Int,
        pageSize: Int
    ): GamesResponse
}