package com.example.videogamesbrowser.ui.details

import com.example.videogamesbrowser.data.remote.dto.GameDetailsResponseDto
import com.example.videogamesbrowser.domain.model.DomainGameDetails

data class GameDetailsUiState(
    val gameDetails: DomainGameDetails?=null,
    val isLoading: Boolean = false,
    val error: String? = null
)
