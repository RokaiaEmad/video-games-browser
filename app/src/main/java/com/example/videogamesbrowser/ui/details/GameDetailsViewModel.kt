package com.example.videogamesbrowser.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.videogamesbrowser.domain.usecase.GetGameDetailsUseCase
import com.example.videogamesbrowser.ui.navigation.GameDetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGameDetailsUseCase: GetGameDetailsUseCase
) : ViewModel() {
    private val gameId = savedStateHandle.get<Int>("gameId") ?: 0
    private val _uiState = MutableStateFlow(GameDetailsUiState())
    val uiState: StateFlow<GameDetailsUiState> = _uiState

    init {
        loadGameDetails()
    }

    fun retry() {
        loadGameDetails()
    }

    private fun loadGameDetails() {
        if (_uiState.value.isLoading) return
        viewModelScope.launch {
            getGameDetailsUseCase(gameId)
                .onStart {
//                    _uiState.update {
//                        it.copy(
//                            isLoading = true,
//                            error = null
//                        )
//                    }

                }
                .catch { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
                .collect { result->
                    _uiState.update {
                        it.copy(
                            gameDetails = result,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }
}