package com.example.esgrima.model

data class Competicion(
    val nombre: String,
    val entidadOrganizadora: String,
    val fecha: String,
    val lugar: String,
    val armas: Modalidad,
    val arbitros: List<Arbitro> = mutableListOf(),
    val competidores: List<Tirador> = mutableListOf(),
)
