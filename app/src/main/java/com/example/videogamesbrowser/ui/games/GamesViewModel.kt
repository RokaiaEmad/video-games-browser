package com.example.videogamesbrowser.ui.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.videogamesbrowser.domain.model.DomainGame
import com.example.videogamesbrowser.domain.usecase.GetGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    getGameUseCase: GetGameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GamesUiState())
    val uiState: StateFlow<GamesUiState> = _uiState.asStateFlow()

    val gamesPagingFlow = getGameUseCase()
        .cachedIn(viewModelScope)

    fun onSearchQueryChanged(query: String, games: List<DomainGame>) {
        val filteredGames = filterGames(games = games, query = query)
        _uiState.update {
            it.copy(
                searchQuery = query,
                filteredGames = filteredGames,
                searching = query.isNotEmpty()
            )
        }
    }

    private fun filterGames(games: List<DomainGame>, query: String): List<DomainGame> {
        return if (query.isBlank()) games
        else games.filter { it.name.contains(query, ignoreCase = true) }
    }
}