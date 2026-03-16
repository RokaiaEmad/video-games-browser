package com.example.videogamesbrowser.ui.games

import androidx.compose.foundation.clickable
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
import com.example.videogamesbrowser.data.remote.model.Game

@Composable
fun GameItem(
    game: Game,
    onClick: (Int) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick(game.id)
            }
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