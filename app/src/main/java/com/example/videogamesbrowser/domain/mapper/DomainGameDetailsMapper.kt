package com.example.videogamesbrowser.domain.mapper

import com.example.videogamesbrowser.data.remote.dto.GameDetailsResponseDto
import com.example.videogamesbrowser.domain.model.DomainGameDetails

fun GameDetailsResponseDto.toDomain() = DomainGameDetails(
    id = id ?: 0,
    name = name.orEmpty(),
    description = description.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    rating = rating ?: 0.0,
    released = released.orEmpty()
)