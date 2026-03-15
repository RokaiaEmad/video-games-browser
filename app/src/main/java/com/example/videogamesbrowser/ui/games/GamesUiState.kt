package com.example.videogamesbrowser.ui.games

import com.example.videogamesbrowser.domain.model.response.Game

data class GamesUiState(
    val games: List<Game> = emptyList(),
    val filteredGames: List<Game> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null
)
