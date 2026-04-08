package com.example.pokeapi.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class RetrofitFactory {

    private val BASE_URL = "https://pokeapi.co"

    private val retrofitFactory = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun getPokemonService(): PokemonService {
        return retrofitFactory.create(PokemonService::class.java)
    }
}