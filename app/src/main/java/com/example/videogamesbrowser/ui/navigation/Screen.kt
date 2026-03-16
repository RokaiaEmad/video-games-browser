package com.example.videogamesbrowser.ui.navigation

sealed class Screen (val route:String){
    data object Games : Screen("games")
    data object Details : Screen("details/{gameId}") {
        fun createRoute(gameId: Int): String = "details/$gameId"
    }
}