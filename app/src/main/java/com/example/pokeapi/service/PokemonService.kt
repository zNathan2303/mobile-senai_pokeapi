package com.example.pokeapi.service

import com.example.pokeapi.model.PokemonDetails
import com.example.pokeapi.model.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("/api/v2/pokemon/")
    fun getAllPokemon(): Call<PokemonList>

    @GET("/api/v2/pokemon/{name}")
    fun getPokemonDetails(@Path("name") name: String): Call<PokemonDetails>
}