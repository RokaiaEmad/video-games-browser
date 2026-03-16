package com.example.videogamesbrowser.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.videogamesbrowser.data.datasource.MainDataSource
import com.example.videogamesbrowser.data.local.GameDao
import com.example.videogamesbrowser.data.local.GameEntity
import com.example.videogamesbrowser.utils.Constants

@OptIn(ExperimentalPagingApi::class)
class GamesRemoteMediator(
    private val mainDataSource: MainDataSource,
    private val gameDao: GameDao
) : RemoteMediator<Int, GameEntity>() {

    private var currentPage = 1

    override suspend fun initialize(): InitializeAction {
        val hasCache = gameDao.getGamesCount() > 0
        return if (hasCache) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    currentPage = 1
                    1
                }
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    currentPage++
                    currentPage
                }
            }

            val response = mainDataSource.getGames(
                genre = Constants.DEFAULT_GENRE,
                page = page,
                pageSize = state.config.pageSize
            )

            val games = response.results.map { game ->
                GameEntity(
                    id = game.id,
                    name = game.name,
                    backgroundImage = game.backgroundImage,
                    rating = game.rating
                )
            }

            if (loadType == LoadType.REFRESH) {
                gameDao.clearGames()
            }
            gameDao.insertGames(games)

            MediatorResult.Success(endOfPaginationReached = games.isEmpty())

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}