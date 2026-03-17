package com.example.videogamesbrowser.data.remote.api

import com.example.videogamesbrowser.data.remote.dto.GameDetailsResponseDto
import com.example.videogamesbrowser.data.remote.dto.GameResponseDto
import com.example.videogamesbrowser.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesApiService {
    @GET(Constants.ENDPOINT_GAMES)
    suspend fun getGames(
        @Query(Constants.QUERY_GENRES) genre: String,
        @Query(Constants.QUERY_PAGE) page: Int,
        @Query(Constants.QUERY_PAGE_SIZE) pageSize: Int
    ): GameResponseDto

    @GET(Constants.ENDPOINT_GAME_DETAILS)
    suspend fun getGameDetails(
        @Path(Constants.PATH_ID) id: Int,
    ): GameDetailsResponseDto
}