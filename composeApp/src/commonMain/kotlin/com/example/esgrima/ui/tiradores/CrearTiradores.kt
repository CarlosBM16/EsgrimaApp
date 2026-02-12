package com.example.esgrima.ui.tiradores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.esgrima.model.Direccion
import com.example.esgrima.model.Modalidad
import com.example.esgrima.model.Tirador

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearTiradores(
    onTiradorGuardado: (Tirador) -> Unit,
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

    // Estado de Modalidad (Dropdown)
    var expanded by remember { mutableStateOf(false) }
    var modalidadSeleccionada by remember { mutableStateOf(Modalidad.ESPADA) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Tirador") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver atrás"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Nuevo Tirador", style = MaterialTheme.typography.headlineSmall)

            // Datos Personales
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = genero,
                    onValueChange = { genero = it },
                    label = { Text("Género") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = edad,
                    onValueChange = { if (it.all { char -> char.isDigit() }) edad = it },
                    label = { Text("Edad") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = modalidadSeleccionada.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Modalidad") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    Modalidad.entries.forEach { mod ->
                        DropdownMenuItem(
                            text = { Text(mod.name) },
                            onClick = {
                                modalidadSeleccionada = mod
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = telefono,
                onValueChange = { if (it.all { c -> c.isDigit() }) telefono = it },
                label = { Text("Teléfono de Contacto") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            // Datos del Club y Federación
            OutlinedTextField(
                value = club,
                onValueChange = { club = it },
                label = { Text("Club") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = nFederacion,
                onValueChange = { if (it.all { c -> c.isDigit() }) nFederacion = it },
                label = { Text("Nº Federación") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Sección Dirección
            Text("Localización", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(value = calle, onValueChange = { calle = it }, label = { Text("Calle / Vía") }, modifier = Modifier.fillMaxWidth())

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = ciudad, onValueChange = { ciudad = it }, label = { Text("Ciudad") }, modifier = Modifier.weight(1f))
                OutlinedTextField(value = provincia, onValueChange = { provincia = it }, label = { Text("Provincia") }, modifier = Modifier.weight(1f))
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = codPostal,
                    onValueChange = { if (it.all { c -> c.isDigit() }) codPostal = it },
                    label = { Text("C. Postal") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(value = pais, onValueChange = { pais = it }, label = { Text("País") }, modifier = Modifier.weight(1f))
            }



            Spacer(modifier = Modifier.height(16.dp))

            // Botón Guardar
            Button(
                onClick = {
                    val nuevaDireccion = Direccion(
                        calle = calle,
                        ciudad = ciudad,
                        provincia = provincia,
                        codPostal = codPostal.toIntOrNull() ?: 0
                    )

                    val tirador = Tirador(
                        nombre = nombre,
                        apellido = apellido,
                        genero = genero,
                        edad = edad.toIntOrNull() ?: 0,
                        numeroFederacion = nFederacion.toIntOrNull() ?: 0,
                        club = club,
                        pais = pais,
                        modalidad = modalidadSeleccionada,
                        direccion = nuevaDireccion,
                        nTelefono = telefono.toIntOrNull() ?: 0
                    )
                    onTiradorGuardado(tirador)
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Finalizar Registro")
            }
        }
    }


}