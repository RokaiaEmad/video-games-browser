package com.example.videogamesbrowser.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.videogamesbrowser.data.datasource.MainDataSource
import com.example.videogamesbrowser.data.local.GameDao
import com.example.videogamesbrowser.data.paging.GamesRemoteMediator
import com.example.videogamesbrowser.data.remote.model.Game
import com.example.videogamesbrowser.data.remote.model.GameDetailsResponse
import com.example.videogamesbrowser.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource,
    private val gameDao: GameDao
) : GameRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getGames(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            remoteMediator = GamesRemoteMediator(mainDataSource, gameDao),
            pagingSourceFactory = { gameDao.getGames() }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                Game(
                    id = entity.id,
                    name = entity.name,
                    backgroundImage = entity.backgroundImage,
                    rating = entity.rating
                )
            }
        }
    }

    override suspend fun getGameDetails(id: Int): GameDetailsResponse {
        return mainDataSource.getGameDetails(id)
    }
}