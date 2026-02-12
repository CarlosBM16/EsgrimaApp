package com.example.esgrima.data

import com.example.esgrima.model.Arbitro
import com.example.esgrima.model.competicion.Competicion
import com.example.esgrima.model.Direccion
import com.example.esgrima.model.Modalidad
import com.example.esgrima.model.Tirador
import com.example.esgrima.model.competicion.Combate
import com.example.esgrima.model.competicion.EstadoCompeticion
import com.example.esgrima.model.competicion.Poule
import com.example.esgrima.model.competicion.RegistroClasificacion

object DataRepository {
    // Listas mutables para poder añadir elementos
    val listaTiradores = mutableListOf<Tirador>()
    val listaArbitros = mutableListOf<Arbitro>()
    val listaCompeticiones = mutableListOf<Competicion>()

    init {
        generarDatosPrueba()
    }

    fun guardadoTirador(tirador: Tirador) {
        listaTiradores.add(tirador)
    }

    fun guardadoArbitro(arbitro: Arbitro) {
        listaArbitros.add(arbitro)
    }

    fun guardadoCompeticion(competicion: Competicion) {
        listaCompeticiones.add(competicion)
    }

    private fun generarDatosPrueba() {
        // --- 10 TIRADORES (Cosmere) ---
        listaTiradores.addAll(
            listOf(
                Tirador("Kaladin", "Bendito por la Tormenta", "M", 20, 101, "Puente Cuatro", "Roshar", Modalidad.ESPADA, Direccion("Calle Honor 4", "Hearthstone", "Alethkar", 10004), 600000001),
                Tirador("Shallan", "Davar", "F", 18, 102, "Luminosos", "Roshar", Modalidad.FLORETE, Direccion("Senda de los Reyes", "Jah Keved", "Vedenar", 10002), 600000002),
                Tirador("Adolin", "Kholin", "M", 23, 103, "Duelos Kholinar", "Roshar", Modalidad.SABLE, Direccion("Palacio Real", "Kholinar", "Alethkar", 10003), 600000003),
                Tirador("Vin", "Ventanejo", "F", 19, 104, "Banda de Kelsier", "Scadrial", Modalidad.FLORETE, Direccion("Calle de la Bruma", "Luthadel", "Dominio Central", 20004), 600000004),
                Tirador("Kelsier", "Superviviente", "M", 34, 105, "Banda de Kelsier", "Scadrial", Modalidad.SABLE, Direccion("Pozo de la Ascensión", "Luthadel", "Dominio Central", 20005), 600000005),
                Tirador("Sazed", "Terris", "M", 45, 106, "Sínodo", "Scadrial", Modalidad.ESPADA, Direccion("Calle de los Recuerdos", "Tathingdwen", "Terris", 20006), 600000006),
                Tirador("Vasher", "Guerra Mental", "M", 500, 107, "Retornados", "Nalthis", Modalidad.ESPADA, Direccion("Calle de los Colores", "T'Telir", "Hallandren", 30007), 600000007),
                Tirador("Vivenna", "Hallandren", "F", 25, 108, "Familia Real", "Nalthis", Modalidad.FLORETE, Direccion("Palacio de Colores", "T'Telir", "Hallandren", 30008), 600000008),
                Tirador("Raoden", "Arelon", "M", 28, 109, "Aritméticos", "Sel", Modalidad.ESPADA,
                    Direccion("Muros de Elantris", "Elantris", "Arelon", 40009), 600000009),
                Tirador("Sarene", "Teod", "F", 26, 110, "Diplomacia Teod", "Sel", Modalidad.SABLE, Direccion("Puerto de Kae", "Kae", "Arelon", 40010), 600000010)
            )
        )

        // --- 10 ÁRBITROS (Cosmere) ---
        listaArbitros.addAll(
            listOf(
                Arbitro("Hoid", "Sagaz", "M", 9999, 1, "Saltamundos", "Cosmere", listOf(Modalidad.ESPADA, Modalidad.FLORETE, Modalidad.SABLE), Direccion("Calle Sin Fin", "Cualquier sitio", "Cosmere", 0), 699999999),
                Arbitro("Dalinar", "Kholin", "M", 50, 2, "Ejército de la Unidad", "Roshar", listOf(Modalidad.ESPADA), Direccion("Campamentos de Guerra", "Llanuras Quebradas", "Alethkar", 10001), 600000111),
                Arbitro("Jasnah", "Kholin", "M", 35, 3, "Eruditos de la Luz", "Roshar", listOf(Modalidad.FLORETE, Modalidad.ESPADA), Direccion("Biblioteca Real", "Kholinar", "Alethkar", 10001), 600000112),
                Arbitro("Elend", "Venture", "M", 24, 4, "Nuevo Imperio", "Scadrial", listOf(Modalidad.ESPADA), Direccion("Palacio de Cristal", "Luthadel", "Scadrial", 20001), 600000113),
                Arbitro("Marsh", "Ironeyes", "M", 300, 5, "Inquisidores", "Scadrial", listOf(Modalidad.SABLE, Modalidad.ESPADA), Direccion("Calle Acero", "Luthadel", "Scadrial", 20002), 600000114),
                Arbitro("Navani", "Kholin", "F", 52, 6, "Fabriales", "Roshar", listOf(Modalidad.FLORETE), Direccion("Torre de Urithiru", "Urithiru", "Roshar", 10000), 600000115),
                Arbitro("Szeth", "Vallano", "M", 35, 7, "Sin Verdad de Shinovar", "Roshar", listOf(Modalidad.SABLE, Modalidad.ESPADA), Direccion("Picos de Piedra", "Shinovar", "Roshar", 10007), 600000116),
                Arbitro("Eshonai", "Parshendi", "F", 30, 8, "Oyentes", "Roshar", listOf(Modalidad.ESPADA), Direccion("Nadir de la Tormenta", "Llanuras Quebradas", "Roshar", 10008), 600000117),
                Arbitro("Lightsong", "El Valiente", "M", 100, 9, "Panteón de Dioses", "Nalthis", listOf(Modalidad.FLORETE, Modalidad.SABLE), Direccion("Corte de los Dioses", "T'Telir", "Nalthis", 30009), 600000118),
                Arbitro("Silence", "Montane", "F", 40, 10, "Posada de las Sombras", "Treno", listOf(Modalidad.SABLE), Direccion("Bosques del Infierno", "Treno", "Treno", 50001), 600000119)
            )
        )

        // 2. Ahora generamos las competiciones usando esos datos
        if (listaTiradores.isNotEmpty() && listaArbitros.isNotEmpty()) {
            listaCompeticiones.addAll(
                listOf(
                    Competicion(
                        nombre = "Torneo de las Llanuras Quebradas",
                        entidadOrganizadora = "Casa Kholin",
                        fecha = "15/05/2026",
                        lugar = "Urithiru - Sala de Duelos",
                        armas = Modalidad.ESPADA,
                        // Asignamos algunos tiradores y árbitros ya existentes
                        competidores = listaTiradores.filter { it.modalidad == Modalidad.ESPADA }.take(4),
                        arbitros = listaArbitros.filter { it.modalidad.contains(Modalidad.ESPADA) }.take(2)
                    ),
                    Competicion(
                        nombre = "Copa de la Bruma",
                        entidadOrganizadora = "Banda de Kelsier",
                        fecha = "20/06/2026",
                        lugar = "Luthadel - Plaza Central",
                        armas = Modalidad.SABLE,
                        competidores = listaTiradores.filter { it.modalidad == Modalidad.SABLE }.take(3),
                        arbitros = listaArbitros.filter { it.modalidad.contains(Modalidad.SABLE) }.take(1)
                    ),
                    Competicion(
                        nombre = "Open de Hallandren",
                        entidadOrganizadora = "Corte de los Dioses",
                        fecha = "10/08/2026",
                        lugar = "T'Telir - Palacio de Colores",
                        armas = Modalidad.FLORETE,
                        competidores = listaTiradores.filter { it.modalidad == Modalidad.FLORETE }.take(3),
                        arbitros = listaArbitros.filter { it.modalidad.contains(Modalidad.FLORETE) }.take(2)
                    )
                )
            )
        }
    }

    fun generarPoules(competicion: Competicion) {
        val inscritos = competicion.competidores.shuffled() // Mezclamos para que sea aleatorio
        val tamañoPoule = if (inscritos.size <= 7) inscritos.size else 6

        val grupos = inscritos.chunked(tamañoPoule)

        competicion.poules.clear()
        grupos.forEachIndexed { index, tiradoresEnPoule ->
            val nuevaPoule = Poule(numero = index + 1, tiradores = tiradoresEnPoule)

            // Generar todos los cruces posibles (Todos contra todos en la poule)
            for (i in tiradoresEnPoule.indices) {
                for (j in i + 1 until tiradoresEnPoule.indices.last + 1) {
                    nuevaPoule.combates.add(
                        Combate(tiradoresEnPoule[i], tiradoresEnPoule[j])
                    )
                }
            }
            competicion.poules.add(nuevaPoule)
        }
        competicion.estado = EstadoCompeticion.POULES
    }

    fun calcularClasificacion(competicion: Competicion): List<RegistroClasificacion> {
        val tabla = competicion.competidores.associateWith { RegistroClasificacion(it) }

        competicion.poules.forEach { poule ->
            poule.combates.filter { it.terminado }.forEach { combate ->
                val reg1 = tabla[combate.tirador1]!!
                val reg2 = tabla[combate.tirador2]!!

                reg1.combatesTotales++
                reg1.tocadosDados += combate.tocadosT1
                reg1.tocadosRecibidos += combate.tocadosT2

                reg2.combatesTotales++
                reg2.tocadosDados += combate.tocadosT2
                reg2.tocadosRecibidos += combate.tocadosT1

                if (combate.tocadosT1 > combate.tocadosT2) reg1.victorias++
                else if (combate.tocadosT2 > combate.tocadosT1) reg2.victorias++
            }
        }

        // Ordenar por V/M (Victorias/Match) y luego por Índice
        return tabla.values.sortedWith(compareByDescending<RegistroClasificacion> { it.v_m }.thenByDescending { it.indice })
    }

    fun generarDirectas(competicion: Competicion) {
        val clasificados = calcularClasificacion(competicion).map { it.tirador }

        // Determinamos el tamaño de la tabla (potencia de 2: 2, 4, 8, 16, 32...)
        val n = clasificados.size
        val tabla = when {
            n <= 2 -> 2
            n <= 4 -> 4
            n <= 8 -> 8
            n <= 16 -> 16
            else -> 32
        }

        competicion.eliminatorias.clear()
        competicion.tablaActual = tabla

        // Lógica de emparejamiento 1 vs N, 2 vs N-1...
        // Si hay huecos (BYEs), se gestionan dejando al oponente nulo o saltando
        for (i in 0 until tabla / 2) {
            val t1 = clasificados.getOrNull(i)
            val t2 = clasificados.getOrNull(tabla - 1 - i)

            if (t1 != null && t2 != null) {
                competicion.eliminatorias.add(Combate(t1, t2))
            } else if (t1 != null) {
                // El tirador pasa de ronda automáticamente (BYE)
                // Por ahora lo añadimos como un combate terminado 15-0
                competicion.eliminatorias.add(Combate(t1, t1, 15, 0, true))
            }
        }

        competicion.estado = EstadoCompeticion.ELIMINATORIAS
    }

    fun avanzarRonda(competicion: Competicion) {
        // 1. Obtenemos los ganadores de los combates actuales
        val ganadores = competicion.eliminatorias.map { combate ->
            if (combate.tocadosT1 > combate.tocadosT2) combate.tirador1 else combate.tirador2
        }

        // 2. Limpiamos las eliminatorias viejas
        competicion.eliminatorias.clear()

        // 3. Reducimos el tamaño de la tabla (de 8 a 4, de 4 a 2, etc.)
        competicion.tablaActual /= 2

        // 4. Si solo queda 1 ganador, el torneo ha terminado
        if (competicion.tablaActual < 2) {
            competicion.estado = EstadoCompeticion.FINALIZADA
            return
        }

        // 5. Emparejamos a los ganadores para la nueva ronda
        // En las siguientes rondas suelen ir en orden de la lista de ganadores
        for (i in 0 until ganadores.size step 2) {
            val t1 = ganadores[i]
            val t2 = ganadores.getOrNull(i + 1)

            if (t2 != null) {
                competicion.eliminatorias.add(Combate(t1, t2))
            } else {
                // Caso impar (BYE), pasa directamente
                competicion.eliminatorias.add(Combate(t1, t1, 15, 0, true))
            }
        }
    }
}