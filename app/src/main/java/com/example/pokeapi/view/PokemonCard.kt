package com.example.pokeapi.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.pokeapi.model.PokemonDetails
import com.example.pokeapi.model.enums.TypeColor

@Composable
fun PokemonCard(pokemon: PokemonDetails) {

    val typeName = pokemon.types.firstOrNull()?.type?.name
    val typeEnum = TypeColor.fromString(typeName)
    val color = typeEnum?.color ?: Color.Gray

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color),
        colors = CardColors(
            Color.White,
            Color.White,
            Color.White,
            Color.White,
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp,0.dp,8.dp,0.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    text = "#${String.format("%03d", pokemon.id)}",
                    fontSize = 12.sp,
                    color = color
                )
            }

            AsyncImage(
                model = pokemon.sprites?.other?.officialArtwork?.frontDefault,
                contentDescription = pokemon.name,
                modifier = Modifier.size(76.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}
