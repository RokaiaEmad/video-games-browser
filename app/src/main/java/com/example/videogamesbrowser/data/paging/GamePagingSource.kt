package com.example.videogamesbrowser.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.videogamesbrowser.data.datasource.MainDataSource
import com.example.videogamesbrowser.data.remote.dto.GameDto
import com.example.videogamesbrowser.domain.mapper.toDomain
import com.example.videogamesbrowser.domain.model.DomainGame
import com.example.videogamesbrowser.utils.Constants
import com.example.videogamesbrowser.utils.Constants.DEFAULT_GENRE
import com.example.videogamesbrowser.utils.Constants.PAGE_SIZE

class GamesPagingSource(
    private val mainDataSource: MainDataSource
) : PagingSource<Int, GameDto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameDto> {
        return try {

            val page = params.key ?: Constants.START_PAGE

            val response = mainDataSource.getGames(
                genre = DEFAULT_GENRE,
                page = page,
                pageSize = PAGE_SIZE,
            )

            val games = response.results.orEmpty()

            LoadResult.Page(
                data = games,
                prevKey = if (page == Constants.START_PAGE) null else page - 1,
                nextKey = if (games.size < PAGE_SIZE) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GameDto>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

}