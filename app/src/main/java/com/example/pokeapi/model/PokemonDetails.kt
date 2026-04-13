package com.example.pokeapi.model

import com.google.gson.annotations.SerializedName

data class PokemonDetails (
    val name: String = "",
    val id: Int = 0,
    val sprites: Sprites? = null,
    val types: List<PokemonType> = listOf()
)

data class Sprites(
    val other: OtherSprites? = null
)

data class OtherSprites(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork? = null
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String = ""
)

data class PokemonType(
    val slot: Int = 0,
    val type: Type? = null
)

data class Type(
    val name: String = ""
)