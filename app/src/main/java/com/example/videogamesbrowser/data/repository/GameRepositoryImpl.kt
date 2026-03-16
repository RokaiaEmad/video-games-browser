package com.example.videogamesbrowser.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.videogamesbrowser.data.datasource.MainDataSource
import com.example.videogamesbrowser.data.paging.GamesPagingSource
import com.example.videogamesbrowser.data.remote.model.Game
import com.example.videogamesbrowser.data.remote.model.GameDetailsResponse
import com.example.videogamesbrowser.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource,
) : GameRepository {

    override fun getGames(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GamesPagingSource(mainDataSource)
            }
        ).flow
    }


    override suspend fun getGameDetails(id: Int): GameDetailsResponse {
        return mainDataSource.getGameDetails(id)
    }
}