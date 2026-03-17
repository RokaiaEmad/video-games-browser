package com.example.videogamesbrowser.domain.model

data class DomainGameDetails(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val rating: Double,
    val released: String
)
