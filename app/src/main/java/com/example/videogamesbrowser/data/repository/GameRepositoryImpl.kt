package com.example.videogamesbrowser.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.videogamesbrowser.data.datasource.MainDataSource
import com.example.videogamesbrowser.data.paging.GamesPagingSource
import com.example.videogamesbrowser.data.remote.dto.GameDetailsResponseDto
import com.example.videogamesbrowser.data.remote.dto.GameDto
import com.example.videogamesbrowser.domain.model.DomainGame
import com.example.videogamesbrowser.domain.repository.GameRepository
import com.example.videogamesbrowser.utils.Constants.PAGE_SIZE
import com.example.videogamesbrowser.utils.Constants.PREFETCH_DISTANCE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource,
) : GameRepository {

    override fun getGames(): Flow<PagingData<GameDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GamesPagingSource(mainDataSource)
            }
        ).flow
    }


    override suspend fun getGameDetails(id: Int): GameDetailsResponseDto {
        return mainDataSource.getGameDetails(id)
    }
}