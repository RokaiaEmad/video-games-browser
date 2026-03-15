package com.example.videogamesbrowser.domain.repository

import com.example.videogamesbrowser.domain.model.response.Game

interface GameRepository {

    suspend fun getGames(
        genre: String,
        page: Int,
        pageSize: Int
    ): List<Game>

}