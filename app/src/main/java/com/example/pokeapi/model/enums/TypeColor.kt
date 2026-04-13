package com.example.pokeapi.model.enums

import androidx.compose.ui.graphics.Color

enum class TypeColor(
    val typeName: String,
    val color: Color
) {
    GRASS("grass", Color(0xFF78C850)),
    FIRE("fire", Color(0xFFF08030)),
    WATER("water", Color(0xFF6890F0)),
    ELECTRIC("electric", Color(0xFFF8D030)),
    ICE("ice", Color(0xFF98D8D8)),
    FIGHTING("fighting", Color(0xFFC03028)),
    POISON("poison", Color(0xFFA040A0)),
    GROUND("ground", Color(0xFFE0C068)),
    FLYING("flying", Color(0xFFA890F0)),
    PSYCHIC("psychic", Color(0xFFF85888)),
    BUG("bug", Color(0xFFA8B820)),
    ROCK("rock", Color(0xFFB8A038)),
    GHOST("ghost", Color(0xFF705898)),
    DRAGON("dragon", Color(0xFF7038F8)),
    DARK("dark", Color(0xFF705848)),
    STEEL("steel", Color(0xFFB8B8D0)),
    FAIRY("fairy", Color(0xFFEE99AC));

    companion object {
        fun fromString(name: String?): TypeColor? {
            return values().find { it.typeName == name }
        }
    }
}