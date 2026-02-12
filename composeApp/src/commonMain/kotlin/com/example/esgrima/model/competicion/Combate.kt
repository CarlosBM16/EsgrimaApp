package com.example.esgrima.model.competicion

import com.example.esgrima.model.Arbitro
import com.example.esgrima.model.Tirador

data class Combate(
    val tirador1: Tirador,
    val tirador2: Tirador,
    var tocadosT1: Int = 0,
    var tocadosT2: Int = 0,
    var terminado: Boolean = false,
    val arbitro: Arbitro? = null
)