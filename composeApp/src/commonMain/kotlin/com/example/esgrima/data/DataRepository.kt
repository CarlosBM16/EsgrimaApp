package com.example.esgrima.data

import com.example.esgrima.model.Arbitro
import com.example.esgrima.model.Competicion
import com.example.esgrima.model.Tirador

object DataRepository {
    // Listas mutables para poder añadir elementos
    val listaTiradores = mutableListOf<Tirador>()
    val listaArbitros = mutableListOf<Arbitro>()
    val listaCompeticiones = mutableListOf<Competicion>()

    fun guardadoTirador(tirador: Tirador) {
        listaTiradores.add(tirador)
    }

    fun guardadoArbitro(arbitro: Arbitro) {
        listaArbitros.add(arbitro)
    }

    fun guardadoCompeticion(competicion: Competicion) {
        listaCompeticiones.add(competicion)
    }
}