package com.example.esgrima.data

import com.example.esgrima.model.Arbitro
import com.example.esgrima.model.Competicion
import com.example.esgrima.model.Direccion
import com.example.esgrima.model.Modalidad
import com.example.esgrima.model.Tirador

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
    }
}