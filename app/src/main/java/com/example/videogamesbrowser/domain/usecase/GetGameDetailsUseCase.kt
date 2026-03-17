package com.example.videogamesbrowser.domain.usecase

import com.example.videogamesbrowser.data.remote.dto.GameDetailsResponseDto
import com.example.videogamesbrowser.domain.mapper.toDomain
import com.example.videogamesbrowser.domain.model.DomainGameDetails
import com.example.videogamesbrowser.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(
        id: Int
    ): Flow<DomainGameDetails> = flow {
        val gameDto = gameRepository.getGameDetails(id)
        emit(gameDto.toDomain())
    }.flowOn(Dispatchers.IO)
}
