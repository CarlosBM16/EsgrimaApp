package com.example.esgrima.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Home(
    onClickCrearTiradores: () -> Unit,
    onClickCrearArbitros: () -> Unit,
    onClickListaCompeticiones: () -> Unit,
    onClickListaTiradores: () -> Unit,
    onClickListaArbitros: () -> Unit,
    onClickCrearCompeticiones: () -> Unit,
    onClickLogOut: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Panel de Control",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp, start = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                DashboardButton(
                    text = "Lista de tiradores",
                    icon = Icons.Default.List,
                    onClick = onClickListaTiradores
                )
            }
            item {
                DashboardButton(
                    text = "Crear Tiradores",
                    icon = Icons.Default.PersonAdd,
                    onClick = onClickCrearTiradores
                )
            }

            item {
                DashboardButton(
                    text = "Lista de tiradores",
                    icon = Icons.Default.List,
                    onClick = onClickListaArbitros
                )
            }

            item {
                DashboardButton(
                    text = "Crear árbitros",
                    icon = Icons.Default.PersonAdd,
                    onClick = onClickCrearArbitros
                )
            }

            item {
                DashboardButton(
                    text = "Lista de competiciones",
                    icon = Icons.Default.List,
                    onClick = onClickListaCompeticiones)
            }

            item {
                DashboardButton(
                    text = "Crear competiciones",
                    icon = Icons.Default.Add,
                    onClick = onClickCrearCompeticiones
                )
            }

            item {
                DashboardButton (
                    text = "Cerrar sesión",
                    icon = Icons.Default.Logout,
                    onClick = onClickLogOut,
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            }
        }
    }
}

@Composable
fun DashboardButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f) // Hace que el botón sea cuadrado
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}