package com.example.esgrima.ui.arbitros

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.esgrima.model.Arbitro
import com.example.esgrima.model.Direccion
import com.example.esgrima.model.Modalidad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearArbitros(
    onArbitroGuardado: (Arbitro) -> Unit,
    onBack: () -> Unit
) {
    // Estados del formulario
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var nFederacion by remember { mutableStateOf("") }
    var club by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    // Estados para la Direccion
    var calle by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var provincia by remember { mutableStateOf("") }
    var codPostal by remember { mutableStateOf("") }

    // Estado para Modalidades (Lista múltiple)
    val modalidadesSeleccionadas = remember { mutableStateListOf<Modalidad>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Árbitro") },
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Datos Personales", style = MaterialTheme.typography.titleMedium)

            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = apellido, onValueChange = { apellido = it }, label = { Text("Apellido") }, modifier = Modifier.fillMaxWidth())

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = genero, onValueChange = { genero = it }, label = { Text("Género") }, modifier = Modifier.weight(1f))
                OutlinedTextField(
                    value = edad,
                    onValueChange = { if (it.all { c -> c.isDigit() }) edad = it },
                    label = { Text("Edad") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            // --- SELECCIÓN MÚLTIPLE DE MODALIDADES ---
            Text("Modalidades tituladas", style = MaterialTheme.typography.titleMedium)
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Modalidad.entries.forEach { modalidad ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Checkbox(
                                checked = modalidadesSeleccionadas.contains(modalidad),
                                onCheckedChange = { isChecked ->
                                    if (isChecked) modalidadesSeleccionadas.add(modalidad)
                                    else modalidadesSeleccionadas.remove(modalidad)
                                }
                            )
                            Text(text = modalidad.name)
                        }
                    }
                }
            }

            OutlinedTextField(
                value = telefono,
                onValueChange = { if (it.all { c -> c.isDigit() }) telefono = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            OutlinedTextField(value = club, onValueChange = { club = it }, label = { Text("Club") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(
                value = nFederacion,
                onValueChange = { if (it.all { c -> c.isDigit() }) nFederacion = it },
                label = { Text("Nº Federación") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Text("Localización", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(value = calle, onValueChange = { calle = it }, label = { Text("Calle") }, modifier = Modifier.fillMaxWidth())

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = ciudad, onValueChange = { ciudad = it }, label = { Text("Ciudad") }, modifier = Modifier.weight(1f))
                OutlinedTextField(value = provincia, onValueChange = { provincia = it }, label = { Text("Provincia") }, modifier = Modifier.weight(1f))
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = codPostal,
                    onValueChange = { if (it.all { c -> c.isDigit() }) codPostal = it },
                    label = { Text("C.P.") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(value = pais, onValueChange = { pais = it }, label = { Text("País") }, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val nuevaDireccion = Direccion(calle, ciudad, provincia, codPostal.toIntOrNull() ?: 0)
                    val nuevoArbitro = Arbitro(
                        nombre = nombre,
                        apellido = apellido,
                        genero = genero,
                        edad = edad.toIntOrNull() ?: 0,
                        numeroFederacion = nFederacion.toIntOrNull() ?: 0,
                        club = club,
                        pais = pais,
                        modalidad = modalidadesSeleccionadas.toList(),
                        direccion = nuevaDireccion,
                        nTelefono = telefono.toIntOrNull() ?: 0
                    )
                    onArbitroGuardado(nuevoArbitro)
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                enabled = modalidadesSeleccionadas.isNotEmpty(), // Opcional: no guardar si no hay modalidad
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Guardar Árbitro")
            }
        }
    }
}