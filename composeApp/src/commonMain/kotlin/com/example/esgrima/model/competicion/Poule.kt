package com.example.esgrima.model.competicion

import com.example.esgrima.model.Tirador

data class Poule(
    val numero: Int,
    val tiradores: List<Tirador>,
    val combates: MutableList<Combate> = mutableListOf()
)
