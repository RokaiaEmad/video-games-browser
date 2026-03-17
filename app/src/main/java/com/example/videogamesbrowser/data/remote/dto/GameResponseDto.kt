package com.example.videogamesbrowser.data.remote.dto

import com.google.gson.annotations.SerializedName


data class GameResponseDto(
    @SerializedName("results")
    val results: List<GameDto> ? = null
)
