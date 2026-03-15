package com.example.videogamesbrowser.domain.model.response

import com.google.gson.annotations.SerializedName

data class Game(
    val id: Int,
    val name: String,
    @SerializedName("background_image")
    val backgroundImage: String?,
    val rating: Double,
)