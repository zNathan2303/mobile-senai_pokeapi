package com.example.pokeapi.view

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.pokeapi.R
import com.example.pokeapi.model.PokemonDetails
import com.example.pokeapi.model.PokemonList
import com.example.pokeapi.model.enums.TypeColor
import com.example.pokeapi.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    var pokemonList: PokemonList? by remember {
        mutableStateOf(null)
    }

    val pokemons = remember {
        mutableStateListOf<PokemonDetails>()
    }

    var nameOrIdInSearch by remember {
        mutableStateOf("")
    }

    fun getPokemonList() {
        RetrofitFactory()
            .getPokemonService()
            .getAllPokemon()
            .enqueue(object: Callback<PokemonList> {
                override fun onResponse(
                    call: Call<PokemonList>,
                    response: Response<PokemonList>
                ) {
                    pokemonList = response.body()!!

                    pokemonList!!.results.forEach { pokemon ->
                        RetrofitFactory()
                            .getPokemonService()
                            .getPokemonDetails(pokemon.name)
                            .enqueue(object: Callback<PokemonDetails> {
                                override fun onResponse(
                                    call: Call<PokemonDetails>,
                                    response: Response<PokemonDetails>
                                ) {
                                    pokemons.add(response.body()!!)
                                }

                                override fun onFailure(
                                    call: Call<PokemonDetails>,
                                    t: Throwable
                                ) {
                                    Log.i("Erro busca detalhes pokemon", "${t.message}")
                                }
                            })
                    }
                }
                override fun onFailure(
                    call: Call<PokemonList>,
                    t: Throwable
                ) {
                    Log.i("Erro busca lista pokemons", "${t.message}")
                }
            })
    }

    getPokemonList()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(213, 35, 35, 255))
                .height(70.dp)
                .padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.poke_logo),
                contentDescription = "Logo",
                tint = Color.White,
                modifier = Modifier
                    .size(35.dp)
            )
            Text(
                text = "Pokédex",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        OutlinedTextField(
            value = nameOrIdInSearch,
            onValueChange = {nameOrIdInSearch = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray
            ),
            shape = RoundedCornerShape(16.dp),
            label = { Text(text = "Nome ou ID") },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (nameOrIdInSearch != "") {
                            RetrofitFactory()
                                .getPokemonService()
                                .getPokemonDetails(nameOrIdInSearch)
                                .enqueue(object: Callback<PokemonDetails> {
                                    override fun onResponse(
                                        call: Call<PokemonDetails>,
                                        response: Response<PokemonDetails>
                                    ) {
                                        pokemons.apply {
                                            clear()
                                            add(response.body()!!)
                                        }
                                    }
                                    override fun onFailure(
                                        call: Call<PokemonDetails>,
                                        t: Throwable
                                    ) {
                                        Log.i("TESTE", "${t.message}")
                                    }
                                })
                        } else {
                            getPokemonList()
                        }

                } ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                }
            },
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pokemons) { pokemon ->
                PokemonCard(pokemon, navController)
            }
        }
    }
}

@Composable
fun PokemonCard(
    pokemon: PokemonDetails,
    navController: NavController,
) {

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
        ),
        onClick = {navController.navigate("pokemon-details")}
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
                    text = "#${pokemon.id.toString().padStart(3, '0')}",
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
