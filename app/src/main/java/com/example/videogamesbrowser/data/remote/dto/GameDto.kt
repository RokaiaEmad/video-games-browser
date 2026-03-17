package com.example.videogamesbrowser.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GameDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("background_image")
    val backgroundImage: String? = null,
    @SerializedName("rating")
    val rating: Double? = null
)