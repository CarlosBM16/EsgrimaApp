package com.example.esgrima.ui.competiciones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.esgrima.model.Competicion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Competicion(
    competicion: Competicion,
    onBack: () -> Unit
) {
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
            // Tarjeta de información general
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("📍 Lugar: ${competicion.lugar}", style = MaterialTheme.typography.bodyLarge)
                    Text("📅 Fecha: ${competicion.fecha}", style = MaterialTheme.typography.bodyLarge)
                    Text("🤺 Arma: ${competicion.armas}", style = MaterialTheme.typography.bodyLarge)
                    Text("🏢 Organiza: ${competicion.entidadOrganizadora}", style = MaterialTheme.typography.bodyLarge)
                }
            }

            // Sección Árbitros
            Text("Árbitros Asignados", style = MaterialTheme.typography.titleLarge)
            if (competicion.arbitros.isEmpty()) {
                Text("No hay árbitros asignados.", style = MaterialTheme.typography.bodyMedium)
            } else {
                competicion.arbitros.forEach { arbitro ->
                    ListItem(
                        headlineContent = { Text("${arbitro.nombre} ${arbitro.apellido}") },
                        supportingContent = { Text(arbitro.club) },
                        leadingContent = { Icon(Icons.Default.Gavel, contentDescription = null) }
                    )
                }
            }

            // Sección Competidores
            Text("Tiradores Inscritos", style = MaterialTheme.typography.titleLarge)
            if (competicion.competidores.isEmpty()) {
                Text("No hay competidores inscritos.", style = MaterialTheme.typography.bodyMedium)
            } else {
                competicion.competidores.forEach { tirador ->
                    ListItem(
                        headlineContent = { Text("${tirador.nombre} ${tirador.apellido}") },
                        supportingContent = { Text(tirador.club) },
                        leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
                    )
                }
            }
        }
    }
}