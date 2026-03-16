package com.example.videogamesbrowser.data.datasource

import com.example.videogamesbrowser.data.remote.api.GamesApiService
import com.example.videogamesbrowser.data.remote.model.GameDetailsResponse
import com.example.videogamesbrowser.data.remote.model.GameResponse
import com.example.videogamesbrowser.utils.Constants
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(val apiService: GamesApiService): MainDataSource {
    override suspend fun getGames(genre: String, page: Int, pageSize: Int): GameResponse {
        return apiService.getGames(
            apiKey = Constants.API_KEY,
            genre = genre,
            page = page,
            pageSize = pageSize
        )
    }

    override suspend fun getGameDetails(id: Int): GameDetailsResponse {
        return apiService.getGameDetails(id, apiKey =Constants.API_KEY )
    }
}