package com.example.esgrima.ui.competiciones

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.esgrima.data.DataRepository
import com.example.esgrima.model.competicion.Competicion
import com.example.esgrima.model.competicion.EstadoCompeticion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Competicion(
    competicion: Competicion,
    onBack: () -> Unit
) {
    // Estado para controlar qué pestaña estamos viendo
    var selectedTab by remember { mutableIntStateOf(0) }
    var faseActual by remember { mutableStateOf(competicion.estado) }
    var refrescoCronometro by remember { mutableIntStateOf(0) }
    val refrescarUI : () -> Unit = { refrescoCronometro++ }

    // Definimos las pestañas dinámicamente según el estado de la competición
    val tabs = when (faseActual) {
        EstadoCompeticion.INSCRIPCION -> listOf("Info", "Inscritos")
        EstadoCompeticion.POULES -> listOf("Combates", "Clasificación")
        EstadoCompeticion.ELIMINATORIAS -> listOf("Directas", "Clasificación")
        EstadoCompeticion.FINALIZADA -> listOf("Resultados")
    }

    key(faseActual, refrescoCronometro) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(competicion.nombre) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                        }
                    }
                )
            },
            floatingActionButton = {
                // Botón para avanzar de fase
                GestionarFAB(
                    competicion = competicion,
                    alCambiarFase = {
                        faseActual = competicion.estado
                        selectedTab = 0 // Reseteamos a la primera pestaña de la nueva fase
                    },
                    alRefrescar = refrescarUI
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {

                // Barra de pestañas
                TabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }

                // Contenido dinámico
                when (competicion.estado) {
                    EstadoCompeticion.INSCRIPCION -> {
                        if (selectedTab == 0) InfoGeneralView(competicion)
                        else ListaInscritosView(competicion)
                    }
                    EstadoCompeticion.POULES -> {
                        if (selectedTab == 0) SeccionPoules(competicion,  refrescarUI)
                        else SeccionClasificacion(competicion)
                    }
                    EstadoCompeticion.ELIMINATORIAS -> {
                        if (selectedTab == 0) SeccionDirectas(competicion)
                        else SeccionClasificacion(competicion)
                    }
                    EstadoCompeticion.FINALIZADA -> {
                        SeccionClasificacion(competicion) // O una vista de podio
                    }
                }
            }
        }
    }


}

@Composable
fun InfoGeneralView(competicion: Competicion) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("📍 Lugar: ${competicion.lugar}")
                Text("📅 Fecha: ${competicion.fecha}")
                Text("🤺 Arma: ${competicion.armas}")
                Text("🏢 Organiza: ${competicion.entidadOrganizadora}")
                Text("📊 Estado: ${competicion.estado}")
            }
        }
    }
}

@Composable
fun ListaInscritosView(competicion: Competicion) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Tiradores inscritos: ${competicion.competidores.size}", style = MaterialTheme.typography.titleMedium)
        competicion.competidores.forEach { tirador ->
            ListItem(
                headlineContent = { Text("${tirador.nombre} ${tirador.apellido}") },
                supportingContent = { Text(tirador.club) },
                leadingContent = { Icon(Icons.Default.Person, null) }
            )
        }
    }
}

@Composable
fun GestionarFAB(
    competicion: Competicion,
    alCambiarFase: () -> Unit,
    alRefrescar: () -> Unit
) {
    when (competicion.estado) {
        EstadoCompeticion.INSCRIPCION -> {
            if (competicion.competidores.size >= 2) {
                ExtendedFloatingActionButton(
                    onClick = {
                        DataRepository.generarPoules(competicion)
                        alCambiarFase() // Esto fuerza a la pantalla a cambiar el 'faseActual'
                    },
                    icon = { Icon(Icons.Default.PlayArrow, null) },
                    text = { Text("Empezar Poules") }
                )
            }
        }
        EstadoCompeticion.POULES -> {
            val todosTerminados = competicion.poules.all { p -> p.combates.all { it.terminado } }
            if (todosTerminados) {
                ExtendedFloatingActionButton(
                    onClick = {
                        DataRepository.generarDirectas(competicion)
                        alCambiarFase() // Cambiamos de pestaña y vista a Directas
                    },
                    icon = { Icon(Icons.Default.SkipNext, null) },
                    text = { Text("Pasar a Directas") }
                )
            }
        }
        else -> {}
    }
}