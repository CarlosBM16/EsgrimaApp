package com.example.esgrima.model

data class Tirador (
    val nombre: String,
    val apellido: String,
    val genero: String,
    val edad: Int,
    val numeroFederacion: Int,
    val club: String,
    val pais: String,
    val modalidad: Modalidad,
    val direccion: Direccion,
    val nTelefono: Int
)