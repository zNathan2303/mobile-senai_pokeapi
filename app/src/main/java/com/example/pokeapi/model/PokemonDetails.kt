package com.example.pokeapi.model

import com.google.gson.annotations.SerializedName

data class PokemonDetails (
    val name: String = "",
    val id: String = "",
    val sprites: PokemonSprites? = null,
    val types: List<PokemonType> = listOf()
)

data class PokemonSprites(
    @SerializedName("front_default")
    val frontDefault: String? = null
)

data class PokemonType(
    val slot: Int = 0,
    val type: Type? = null
)

data class Type(
    val name: String = "",
    val url: String = ""
)