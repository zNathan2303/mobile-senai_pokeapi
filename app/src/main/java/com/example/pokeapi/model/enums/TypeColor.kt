package com.example.pokeapi.model.enums

import androidx.compose.ui.graphics.Color

enum class TypeColor(
    val typeName: String,
    val color: Color
) {
    GRASS("grass", Color(0xFF87BE4D)),
    FIRE("fire", Color(0xFFD68851)),
    WATER("water", Color(0xFF7692DE)),
    ELECTRIC("electric", Color(0xFFECD269)),
    ICE("ice", Color(0xFF98D8D8)),
    FIGHTING("fighting", Color(0xFFC03028)),
    POISON("poison", Color(0xFFA040A0)),
    GROUND("ground", Color(0xFFE0C068)),
    FLYING("flying", Color(0xFFA890F0)),
    PSYCHIC("psychic", Color(0xFFD66984)),
    BUG("bug", Color(0xFFABB656)),
    ROCK("rock", Color(0xFFB8A038)),
    GHOST("ghost", Color(0xFF675693)),
    DRAGON("dragon", Color(0xFF7038F8)),
    DARK("dark", Color(0xFF705848)),
    STEEL("steel", Color(0xFFB7B9CD)),
    FAIRY("fairy", Color(0xFFEE99AC));

    companion object {
        fun fromString(name: String?): TypeColor? {
            return values().find { it.typeName == name }
        }
    }
}