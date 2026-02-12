package com.example.esgrima.ui.competiciones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.esgrima.model.competicion.Combate
import com.example.esgrima.model.competicion.Competicion

@Composable
fun SeccionPoules(competicion: Competicion, onCombateCerrado: () -> Unit) {
    var pouleSeleccionada by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Selector de qué Poule queremos ver
        ScrollableTabRow(selectedTabIndex = pouleSeleccionada) {
            competicion.poules.forEachIndexed { index, poule ->
                Tab(
                    selected = pouleSeleccionada == index,
                    onClick = { pouleSeleccionada = index },
                    text = { Text("Poule ${poule.numero}") }
                )
            }
        }

        val pouleActual = competicion.poules[pouleSeleccionada]

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pouleActual.combates) { combate ->
                // Usamos el componente de edición que permite escribir
                FilaCombateEditable(combate, onStatusChanged = onCombateCerrado)
            }
        }
    }
}

@Composable
fun FilaCombateEditable(
    combate: Combate,
    onStatusChanged: () -> Unit
) {
    // Estas variables 'remember' son las que permiten que el TextField se actualice al teclear
    var score1 by remember { mutableStateOf(combate.tocadosT1.toString()) }
    var score2 by remember { mutableStateOf(combate.tocadosT2.toString()) }
    var esTerminado by remember { mutableStateOf(combate.terminado) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(combate.tirador1.apellido, modifier = Modifier.weight(1f))

            // Campo para los puntos del Tirador 1
            OutlinedTextField(
                value = score1,
                onValueChange = { nuevaCifra ->
                    // Solo permitimos números y máximo 2 dígitos
                    if (nuevaCifra.all { it.isDigit() } && nuevaCifra.length <= 2) {
                        score1 = nuevaCifra
                        combate.tocadosT1 = nuevaCifra.toIntOrNull() ?: 0
                    }
                },
                modifier = Modifier.width(65.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            Text(" - ", modifier = Modifier.padding(horizontal = 4.dp))

            // Campo para los puntos del Tirador 2
            OutlinedTextField(
                value = score2,
                onValueChange = { nuevaCifra ->
                    if (nuevaCifra.all { it.isDigit() } && nuevaCifra.length <= 2) {
                        score2 = nuevaCifra
                        combate.tocadosT2 = nuevaCifra.toIntOrNull() ?: 0
                    }
                },
                modifier = Modifier.width(65.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            Text(
                text = combate.tirador2.apellido,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )

            // El Checkbox es vital para saber cuándo contar este combate en la clasificación
            Checkbox(
                checked = esTerminado,
                onCheckedChange = {
                    esTerminado = it
                    combate.terminado = it
                    onStatusChanged()
                }
            )
        }
    }
}
