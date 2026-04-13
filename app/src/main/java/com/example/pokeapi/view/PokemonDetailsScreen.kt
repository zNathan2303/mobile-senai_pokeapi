package com.example.pokeapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.pokeapi.model.PokemonDetails
import com.example.pokeapi.model.enums.TypeColor

@Composable
fun PokemonDetailScreen(pokemon: PokemonDetails) {

    val typeName = pokemon.types.firstOrNull()?.type?.name
    val typeEnum = TypeColor.fromString(typeName)
    val color = typeEnum?.color ?: Color.Gray

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {

        Column {

            // HEADER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "#${pokemon.id.toString().padStart(3, '0')}",
                    color = Color.White
                )
            }

            // IMAGEM
            AsyncImage(
                model = pokemon.sprites?.other?.officialArtwork?.frontDefault,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // CARD BRANCO
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    // TIPOS
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        pokemon.types.forEach {
                            TypeChip(it.type?.name ?: "") // TODO
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // ABOUT
                    Text(
                        text = "About",
                        color = color,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        AboutItem("6.9 kg", "Weight")
                        AboutItem("0.7 m", "Height")
                        AboutItem("Chlorophyll\nOvergrow", "Moves")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "There is a plant seed on its back right from the day this Pokémon is born.",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // BASE STATS
                    Text(
                        text = "Base Stats",
                        color = color,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    pokemon.stats.forEach {
                        StatItem(
                            name = it.stat?.name?.uppercase() ?: "", // TODO
                            value = it.baseStat,
                            color = color
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TypeChip(type: String) {
    val color = when (type.lowercase()) {
        "grass" -> Color(0xFF78C850)
        "poison" -> Color(0xFFA040A0)
        else -> Color.Gray
    }

    Box(
        modifier = Modifier
            .background(color, RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = type.replaceFirstChar { it.uppercase() },
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
fun AboutItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold)
        Text(label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun StatItem(name: String, value: Int, color: Color) {

    val progress = value / 100f

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = name,
            modifier = Modifier.width(60.dp),
            fontSize = 12.sp
        )

        Text(
            text = value.toString().padStart(3, '0'),
            modifier = Modifier.width(40.dp),
            fontSize = 12.sp
        )

        LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
        color = color,
        trackColor = ProgressIndicatorDefaults.linearTrackColor,
        strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )
    }
}