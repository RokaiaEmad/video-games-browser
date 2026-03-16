package com.example.videogamesbrowser.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.videogamesbrowser.ui.details.GameDetailsScreen
import com.example.videogamesbrowser.ui.games.GamesScreen

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Games.route,
        modifier = modifier
    ) {
        composable(route = Screen.Games.route) {
            GamesScreen(
                onGameClick = { gameId ->
                    navController.navigate(Screen.Details.createRoute(gameId))
                }
            )
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("gameId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getInt("gameId") ?: return@composable
            GameDetailsScreen(id = gameId)
        }
    }

}