package com.example.videogamesbrowser.domain.repository

import androidx.paging.PagingData
import com.example.videogamesbrowser.data.remote.model.GameDetailsResponse
import com.example.videogamesbrowser.data.remote.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun getGames(): Flow<PagingData<Game>>


    suspend fun getGameDetails(id:Int): GameDetailsResponse

}