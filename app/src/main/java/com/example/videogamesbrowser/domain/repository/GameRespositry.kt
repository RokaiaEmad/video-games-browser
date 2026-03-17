package com.example.videogamesbrowser.domain.repository

import androidx.paging.PagingData
import com.example.videogamesbrowser.data.remote.dto.GameDetailsResponseDto
import com.example.videogamesbrowser.data.remote.dto.GameDto
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun getGames(): Flow<PagingData<GameDto>>


    suspend fun getGameDetails(id:Int): GameDetailsResponseDto

}