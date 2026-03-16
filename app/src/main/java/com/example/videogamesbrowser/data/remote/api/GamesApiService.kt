package com.example.videogamesbrowser.data.remote.api

import com.example.videogamesbrowser.data.remote.model.GameDetailsResponse
import com.example.videogamesbrowser.data.remote.model.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface GamesApiService {
    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String,
        @Query("genres") genre: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): GameResponse

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: Int,
        @Query("key") apiKey: String,
    ): GameDetailsResponse
}