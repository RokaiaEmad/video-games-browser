package com.example.videogamesbrowser.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GameDetailsResponseDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("background_image")
    val imageUrl: String? = null,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("released")
    val released: String? = null
)