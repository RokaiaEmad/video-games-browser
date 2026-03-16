package com.example.videogamesbrowser.ui.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videogamesbrowser.domain.usecase.GetGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GamesUiState())
    val uiState: StateFlow<GamesUiState> = _uiState

    private var currentPage = 1

    init {
        loadGames()
    }

    fun loadNextPage() {
        if (_uiState.value.isLoading || _uiState.value.isLoadingMore) return
        currentPage++
        loadGames(page = currentPage)
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { state ->
            val filtered = if (query.isBlank()) {
                state.games
            } else {
                state.games.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            }
            state.copy(
                searchQuery = query,
                filteredGames = filtered
            )
        }
    }

    fun loadGames(page: Int = 1) {
        if (_uiState.value.isLoading || _uiState.value.isLoadingMore) return

        if (page == 1) {
            currentPage = 1
        }

        viewModelScope.launch {
            if (page == 1) {
                _uiState.update { it.copy(isLoading = true, error = null) }
            } else {
                _uiState.update { it.copy(isLoadingMore = true, error = null) }
            }

            try {
                val result = getGameUseCase(page)
                _uiState.update { state ->

                    val updatedGames =
                        if (page == 1) result else state.games + result

                    val filtered =
                        if (state.searchQuery.isBlank()) {
                            updatedGames
                        } else {
                            updatedGames.filter {
                                it.name.contains(state.searchQuery, ignoreCase = true)
                            }
                        }

                    state.copy(
                        games = updatedGames,
                        filteredGames = filtered,
                        isLoading = false,
                        isLoadingMore = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        error = e.message
                    )
                }
            }
        }
    }
}