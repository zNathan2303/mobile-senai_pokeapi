package com.example.pokeapi.model

data class PokemonList (
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<MinPokemon> = listOf()
)

data class MinPokemon (
    val name: String = "",
    val url: String = ""
)