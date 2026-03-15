package com.example.videogamesbrowser.domain.model.response

import com.google.gson.annotations.SerializedName


data class GamesResponse(
    val results: List<Game>
)
