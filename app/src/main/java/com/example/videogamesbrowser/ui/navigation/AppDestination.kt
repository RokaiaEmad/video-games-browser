package com.example.videogamesbrowser.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object GamesDestination

@Serializable
data class GameDetailsDestination(
    val gameId: Int
)