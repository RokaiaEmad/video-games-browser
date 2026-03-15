package com.example.videogamesbrowser.data.datasource

import com.example.videogamesbrowser.data.remote.api.GamesApiService
import com.example.videogamesbrowser.domain.model.response.GamesResponse
import com.example.videogamesbrowser.utils.Constants
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(val apiService: GamesApiService): MainDataSource {
    override suspend fun getGames(genre: String, page: Int, pageSize: Int): GamesResponse {
        return apiService.getGames(
            apiKey = Constants.API_KEY,
            genre = genre,
            page = page,
            pageSize = pageSize
        )
    }
}