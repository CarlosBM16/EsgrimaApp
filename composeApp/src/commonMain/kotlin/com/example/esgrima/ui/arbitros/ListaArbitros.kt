package com.example.esgrima.ui.arbitros

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Gavel // Icono más apropiado para árbitro
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.esgrima.data.DataRepository
import com.example.esgrima.model.Arbitro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaArbitros(
    onBack: () -> Unit
) {
    // Obtenemos la lista de árbitros del repositorio
    val lista = DataRepository.listaArbitros

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Árbitros") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (lista.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay árbitros registrados", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(lista) { arbitro ->
                    CardArbitro(arbitro)
                }
            }
        }
    }
}

@Composable
fun CardArbitro(arbitro: Arbitro) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono de mazo/juez para árbitros
            Icon(
                imageVector = Icons.Default.Gavel,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "${arbitro.nombre} ${arbitro.apellido}",
                    style = MaterialTheme.typography.titleMedium
                )

                // Mostramos todas las modalidades separadas por comas
                val modalidadesTexto = arbitro.modalidad.joinToString(", ") { it.name }
                Text(
                    text = "Modalidades: $modalidadesTexto",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Club: ${arbitro.club} | Fed: ${arbitro.numeroFederacion}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}