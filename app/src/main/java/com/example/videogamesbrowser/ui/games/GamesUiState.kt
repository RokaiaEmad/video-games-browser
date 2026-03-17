package com.example.videogamesbrowser.ui.games

import com.example.videogamesbrowser.domain.model.DomainGame

data class GamesUiState(
    val filteredGames: List<DomainGame> = emptyList(),
    val searching: Boolean = false,
    val searchQuery: String = "",
)
