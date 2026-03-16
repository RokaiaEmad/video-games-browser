package com.example.videogamesbrowser.ui.games

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.videogamesbrowser.ui.common.ErrorContent
import com.example.videogamesbrowser.ui.common.LoadingContent

@Composable
fun GamesScreen(
    onGameClick: (Int) -> Unit,
    viewModel: GamesViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem =
                listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            lastVisibleItem >= state.games.size - 3
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            viewModel.loadNextPage()
        }
    }

    when {

        state.isLoading -> {
            LoadingContent()
        }

        state.error != null -> {
            ErrorContent(state.error!!) {
                viewModel.loadGames()
            }
        }

        else -> {
            Column {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = {
                        viewModel.onSearchQueryChanged(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = { Text("Search games") }
                )
                LazyColumn(
                    state = listState
                ) {

                    items(state.filteredGames) { game ->
                        GameItem(
                            game,
                            onClick = { id ->
                                onGameClick(id)
                            })
                    }

                    if (state.isLoadingMore) {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}