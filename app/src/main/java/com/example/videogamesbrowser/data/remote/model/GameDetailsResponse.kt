package com.example.videogamesbrowser.data.remote.model

import com.google.gson.annotations.SerializedName

data class GameDetailsResponse(
    val id: Int,
    val name: String,
    @SerializedName("background_image")
    val imageUrl: String?,
    val rating: Double,
    val released: String?,
    val description: String?
)