package com.example.videogamesbrowser.domain.repository

import com.example.videogamesbrowser.data.remote.model.GameDetailsResponse
import com.example.videogamesbrowser.data.remote.model.Game

interface GameRepository {

    suspend fun getGames(
        genre: String,
        page: Int,
        pageSize: Int
    ): List<Game>

    suspend fun getGameDetails(id:Int): GameDetailsResponse

}