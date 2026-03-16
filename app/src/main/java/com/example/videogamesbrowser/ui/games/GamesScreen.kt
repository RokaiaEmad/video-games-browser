package com.example.videogamesbrowser.ui.games

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.videogamesbrowser.ui.common.ErrorContent
import com.example.videogamesbrowser.ui.common.LoadingContent

@Composable
fun GamesScreen(
    onGameClick: (Int) -> Unit,
    viewModel: GamesViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pagingItems = viewModel.gamesPagingFlow.collectAsLazyPagingItems()

    val isSearching = state.searchQuery.isNotBlank()

    when (val refreshState = pagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingContent()
        }

        is LoadState.Error -> {
            ErrorContent(refreshState.error.message ?: "Unknown error") {
                pagingItems.retry()
            }
        }

        else -> {
            Column {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = viewModel::onSearchQueryChanged,
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    label = { Text("Search games") }
                )

                LazyColumn {
                    if (isSearching) {
                        val filtered = pagingItems.itemSnapshotList.items
                            .filter { game ->
                                game.name.contains(state.searchQuery, ignoreCase = true)
                            }

                        items(items = filtered, key = { it.id }) { game ->
                            GameItem(game = game, onClick = onGameClick)
                        }

                        if (filtered.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxWidth().padding(32.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("No games found for \"${state.searchQuery}\"")
                                }
                            }
                        }

                    } else {
                        items(
                            count = pagingItems.itemCount,
                            key = pagingItems.itemKey { it.id }
                        ) { index ->
                            val game = pagingItems[index]
                            if (game != null) {
                                GameItem(game = game, onClick = onGameClick)
                            }
                        }

                        when (val appendState = pagingItems.loadState.append) {
                            is LoadState.Loading -> {
                                item {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                                    }
                                }
                            }
                            is LoadState.Error -> {
                                item {
                                    ErrorContent(appendState.error.message ?: "Failed to load more") {
                                        pagingItems.retry()
                                    }
                                }
                            }
                            else -> Unit
                        }
                    }
                }
            }
        }
    }
}