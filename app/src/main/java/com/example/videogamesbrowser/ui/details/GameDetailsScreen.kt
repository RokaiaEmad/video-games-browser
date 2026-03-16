package com.example.videogamesbrowser.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.videogamesbrowser.ui.common.ErrorContent
import com.example.videogamesbrowser.ui.common.LoadingContent

@Composable
fun GameDetailsScreen(
    id: Int,
    viewModel: GameDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadGameDetails(id)
    }

    when {
        state.isLoading -> {
            LoadingContent()
        }

        state.error != null -> {
            ErrorContent(state.error!!) {
                viewModel.loadGameDetails(id)
            }
        }

        state.gameDetails != null -> {
            state.gameDetails?.let { game ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        AsyncImage(
                            model = game.imageUrl,
                            contentDescription = game.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    item {
                        Text(
                            text = game.name,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }

                    item {
                        Text("Release date: ${game.released ?: "Unknown"}")
                    }

                    item {
                        Text("Rating: ${game.rating ?: 0.0}")
                    }

                    item {
                        Text(game.description ?: "No description available")
                    }
                }
            }
        }
    }
}