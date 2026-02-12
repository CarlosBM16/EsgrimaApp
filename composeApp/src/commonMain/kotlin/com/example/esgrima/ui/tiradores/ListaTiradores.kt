package com.example.esgrima.ui.tiradores

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.esgrima.data.DataRepository
import com.example.esgrima.model.Tirador

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTiradores(
    onBack: () -> Unit
) {
    // Obtenemos la lista directamente del repositorio
    val lista = DataRepository.listaTiradores

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Tiradores") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (lista.isEmpty()) {
            // Mensaje si no hay datos
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay tiradores registrados", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            // Lista con scroll
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(lista) { tirador ->
                    CardTirador(tirador)
                }
            }
        }
    }
}

@Composable
fun CardTirador(tirador: Tirador) {
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
            // Icono representativo
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "${tirador.nombre} ${tirador.apellido}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Modalidad: ${tirador.modalidad.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "Club: ${tirador.club}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}