package com.example.videogamesbrowser.ui.games

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.videogamesbrowser.domain.model.response.Game

@Composable
fun GameItem(game: Game) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Row {

            AsyncImage(
                model = game.backgroundImage,
                contentDescription = game.name,
                modifier = Modifier.size(100.dp)
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {

                Text(text = game.name)

                Text(text = "⭐ ${game.rating}")

            }
        }
    }
}