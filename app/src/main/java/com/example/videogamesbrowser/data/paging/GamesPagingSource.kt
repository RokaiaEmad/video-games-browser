package com.example.videogamesbrowser.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.videogamesbrowser.data.datasource.MainDataSource
import com.example.videogamesbrowser.data.remote.model.Game
import com.example.videogamesbrowser.utils.Constants

class GamesPagingSource(
    private val mainDataSource: MainDataSource
): PagingSource<Int, Game>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {

            val page = params.key ?: 1

            val response = mainDataSource.getGames(
                genre = Constants.DEFAULT_GENRE ,
                page = page,
                pageSize = 6,
            )

            val games = response.results

            LoadResult.Page(
                data = games,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (games.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

}