package com.example.videogamesbrowser.utils

object Constants {
    const val BASE_URL = "https://api.rawg.io/api/"
    const val API_KEY = "eeb3dcfcf990470d85ed13f7a99f39ec"

    const val ENDPOINT_GAMES = "games"
    const val ENDPOINT_GAME_DETAILS = "games/{id}"
    const val PATH_ID = "id"

    const val QUERY_GENRES = "genres"
    const val QUERY_PAGE = "page"
    const val QUERY_PAGE_SIZE = "page_size"
    const val DEFAULT_GENRE = "action"
    const val PAGE_SIZE = 20
    const val PREFETCH_DISTANCE = 2
    const val START_PAGE = 1
}