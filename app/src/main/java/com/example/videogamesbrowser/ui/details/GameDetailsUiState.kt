package com.example.videogamesbrowser.ui.details

import com.example.videogamesbrowser.data.remote.model.GameDetailsResponse

data class GameDetailsUiState(
    val gameDetails: GameDetailsResponse?=null,
    val isLoading: Boolean = false,
    val error: String? = null
)
