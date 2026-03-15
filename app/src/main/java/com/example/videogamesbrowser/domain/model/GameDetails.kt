package com.example.videogamesbrowser.domain.model

data class GameDetails(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val rating: Double,
    val released: String?,
    val description: String?
)