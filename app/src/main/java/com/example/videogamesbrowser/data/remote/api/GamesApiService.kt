package com.example.videogamesbrowser.data.remote.api

import com.example.videogamesbrowser.data.remote.dto.GameDetailsResponseDto
import com.example.videogamesbrowser.data.remote.dto.GameResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesApiService {
    @GET("games")
    suspend fun getGames(
        @Query("genres") genre: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): GameResponseDto

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: Int,
    ): GameDetailsResponseDto
}