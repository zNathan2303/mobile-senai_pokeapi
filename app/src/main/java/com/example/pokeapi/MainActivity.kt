package com.example.pokeapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokeapi.ui.theme.PokeAPITheme
import com.example.pokeapi.view.HomeScreen
import com.example.pokeapi.view.PokemonDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeAPITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable(
                            route = "home"
                        ) {
                            HomeScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                        composable(
                            route = "pokemon-details/{id}"
                        ) { backStackEntry ->

                            val id = backStackEntry.arguments?.getString("id")

                            PokemonDetailScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                pokemonId = id ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}

