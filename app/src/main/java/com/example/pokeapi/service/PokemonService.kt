package com.example.pokeapi.service

import com.example.pokeapi.model.PokemonDetails
import com.example.pokeapi.model.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("/api/v2/pokemon/")
    suspend fun getAllPokemon(): PokemonList

    @GET("/api/v2/pokemon/{nameOrId}")
    suspend fun getPokemonDetails(@Path("nameOrId") nameOrId: String): PokemonDetails
}