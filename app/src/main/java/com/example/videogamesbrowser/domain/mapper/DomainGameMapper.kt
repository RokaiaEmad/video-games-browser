package com.example.videogamesbrowser.domain.mapper

import com.example.videogamesbrowser.data.remote.dto.GameDto
import com.example.videogamesbrowser.domain.model.DomainGame
import kotlin.text.orEmpty

fun GameDto.toDomain(): DomainGame {
    return DomainGame(
        id = id ?: 0,
        name = name.orEmpty(),
        backgroundImage = backgroundImage.orEmpty(),
        rating = rating ?: 0.0
    )
}