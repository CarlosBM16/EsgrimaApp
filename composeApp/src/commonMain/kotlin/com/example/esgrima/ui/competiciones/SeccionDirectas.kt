package com.example.esgrima.ui.competiciones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.esgrima.data.DataRepository
import com.example.esgrima.model.competicion.Competicion

@Composable
fun SeccionDirectas(competicion: Competicion) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Tablón de ${competicion.tablaActual}",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(competicion.eliminatorias) { combate ->
                // Reutilizamos tu CardCombate pero ajustando el límite a 15 puntos
                CardCombateDirecta(combate)
            }
        }

        // Botón para avanzar a la siguiente ronda (ej: de Octavos a Cuartos)
        Button(
            onClick = { DataRepository.avanzarRonda(competicion) },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            enabled = competicion.eliminatorias.all { it.terminado }
        ) {
            Text("Generar Siguiente Ronda")
        }
    }
}