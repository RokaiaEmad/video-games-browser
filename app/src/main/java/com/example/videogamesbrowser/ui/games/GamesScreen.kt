package com.example.videogamesbrowser.ui.games

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.videogamesbrowser.R
import com.example.videogamesbrowser.domain.model.DomainGame
import com.example.videogamesbrowser.ui.common.ErrorContent
import com.example.videogamesbrowser.ui.common.LoadingContent

@Composable
fun GamesScreen(
    onGameClick: (Int) -> Unit,
    viewModel: GamesViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pagingItems = viewModel.gamesPagingFlow.collectAsLazyPagingItems()

    when (val refreshState = pagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingContent()
        }

        is LoadState.Error -> {
            ErrorContent(refreshState.error.message ?: stringResource(R.string.unknown_error)) {
                pagingItems.retry()
            }
        }

        else -> {
            Column {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = {
                        viewModel.onSearchQueryChanged(
                            query = it,
                            games = pagingItems.itemSnapshotList.items
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    label = { Text(stringResource(R.string.search_games)) }
                )

                LazyColumn {
                    when (state.searching) {
                        true -> {
                            searchGamesContent(
                                searchQuery = state.searchQuery,
                                games = state.filteredGames,
                                onGameClick = {onGameClick(it.id)}
                                )
                        }

                        false -> {
                            allGamesContent(
                                pagingItems = pagingItems,
                                onGameClick = {onGameClick(it.id)}
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun LazyListScope.allGamesContent(
    pagingItems: LazyPagingItems<DomainGame>,
    onGameClick: (DomainGame) -> Unit
) {
    items(pagingItems.itemCount) { index ->
        pagingItems[index]?.let { game ->
            GameItem(
                game = game,
                onClick = { onGameClick(game) }
            )
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
                ErrorContent(
                    appendState.error.message
                        ?: stringResource(R.string.failed_to_load_more)
                ) {
                    pagingItems.retry()
                }
            }
        }

        else -> Unit
    }
}

private fun LazyListScope.searchGamesContent(
    searchQuery: String,
    games: List<DomainGame>,
    onGameClick: (DomainGame) -> Unit
) {
    if (games.isEmpty()) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(
                        R.string.no_games_found_for,
                        searchQuery
                    )
                )
            }
        }
        return
    }
    items(items = games, key = { it.id }) { game ->
        GameItem(
            game = game,
            onClick = { onGameClick(game) }
        )
    }
}
