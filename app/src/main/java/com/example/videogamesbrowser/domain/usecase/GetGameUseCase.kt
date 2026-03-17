package com.example.videogamesbrowser.domain.usecase


import androidx.paging.PagingData
import androidx.paging.map
import com.example.videogamesbrowser.data.remote.dto.GameDto
import com.example.videogamesbrowser.domain.mapper.toDomain
import com.example.videogamesbrowser.domain.model.DomainGame
import com.example.videogamesbrowser.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(): Flow<PagingData<DomainGame>> {
        return gameRepository.getGames()
            .map { it.map { gameDto -> gameDto.toDomain() } }
            .flowOn(Dispatchers.IO)
    }
}