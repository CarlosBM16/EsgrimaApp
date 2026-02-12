package com.example.esgrima.ui.competiciones

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.esgrima.model.competicion.Competicion
import com.example.esgrima.model.Modalidad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearCompeticiones(
    onCompeticionGuardada: (Competicion) -> Unit,
    onBack: () -> Unit
) {
    // Estados del formulario
    var nombre by remember { mutableStateOf("") }
    var organizador by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var lugar by remember { mutableStateOf("") }

    // Estado para el Dropdown de Modalidad (Armas)
    var expanded by remember { mutableStateOf(false) }
    var armaSeleccionada by remember { mutableStateOf(Modalidad.ESPADA) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Competición") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Detalles del Evento",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            // Nombre de la competición
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre de la Competición") },
                placeholder = { Text("Ej: Torneo de la Tormenta") },
                modifier = Modifier.fillMaxWidth()
            )

            // Entidad Organizadora
            OutlinedTextField(
                value = organizador,
                onValueChange = { organizador = it },
                label = { Text("Entidad Organizadora") },
                placeholder = { Text("Ej: Casa Kholin") },
                modifier = Modifier.fillMaxWidth()
            )

            // Fecha (Como String por ahora)
            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha") },
                placeholder = { Text("DD/MM/AAAA") },
                modifier = Modifier.fillMaxWidth()
            )

            // Lugar
            OutlinedTextField(
                value = lugar,
                onValueChange = { lugar = it },
                label = { Text("Lugar / Sede") },
                placeholder = { Text("Ej: Urithiru") },
                modifier = Modifier.fillMaxWidth()
            )

            // Selector de Arma (Modalidad)
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = armaSeleccionada.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Arma de la Competición") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    Modalidad.entries.forEach { modalidad ->
                        DropdownMenuItem(
                            text = { Text(modalidad.name) },
                            onClick = {
                                armaSeleccionada = modalidad
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botón de Guardar
            Button(
                onClick = {
                    val nuevaCompeticion = Competicion(
                        nombre = nombre,
                        entidadOrganizadora = organizador,
                        fecha = fecha,
                        lugar = lugar,
                        armas = armaSeleccionada
                        // arbitros y competidores se inician vacíos por defecto
                    )
                    onCompeticionGuardada(nuevaCompeticion)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = nombre.isNotBlank() && organizador.isNotBlank()
            ) {
                Text("Crear Competición")
            }
        }
    }
}