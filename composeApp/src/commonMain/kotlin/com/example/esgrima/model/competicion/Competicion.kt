package com.example.esgrima.model.competicion

import com.example.esgrima.model.Arbitro
import com.example.esgrima.model.Modalidad
import com.example.esgrima.model.Tirador

data class Competicion(
    val nombre: String,
    val entidadOrganizadora: String,
    val fecha: String,
    val lugar: String,
    val armas: Modalidad,
    val arbitros: List<Arbitro> = mutableListOf(),
    val competidores: List<Tirador> = mutableListOf(),
    val poules: MutableList<Poule> = mutableListOf(),
    val clasificacionPostPoules: MutableList<Tirador> = mutableListOf(),
    var estado: EstadoCompeticion = EstadoCompeticion.INSCRIPCION,
    val eliminatorias: MutableList<Combate> = mutableListOf(),
    var tablaActual: Int = 0 // Ejemplo: 16 para octavos, 8 para cuartos...
)

enum class EstadoCompeticion { INSCRIPCION, POULES, ELIMINATORIAS, FINALIZADA }

data class RegistroClasificacion(
    val tirador: Tirador,
    var victorias: Int = 0,
    var combatesTotales: Int = 0,
    var tocadosDados: Int = 0,
    var tocadosRecibidos: Int = 0
) {
    val indice: Int get() = tocadosDados - tocadosRecibidos
    val v_m: Float get() = if (combatesTotales > 0) victorias.toFloat() / combatesTotales else 0f
}