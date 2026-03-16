package com.example.videogamesbrowser.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.videogamesbrowser.ui.details.GameDetailsScreen
import com.example.videogamesbrowser.ui.games.GamesScreen

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = GamesDestination,
        modifier = modifier
    ) {
        composable<GamesDestination>{
            GamesScreen(
                onGameClick = { gameId->
                    navController.navigate(GameDetailsDestination(gameId))
                }
            )
        }
        composable<GameDetailsDestination>{
            GameDetailsScreen()
        }
    }

}