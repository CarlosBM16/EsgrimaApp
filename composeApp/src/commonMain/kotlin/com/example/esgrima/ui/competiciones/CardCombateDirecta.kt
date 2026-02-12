package com.example.esgrima.ui.competiciones

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.esgrima.model.competicion.Combate

@Composable
fun CardCombateDirecta(
    combate: Combate,
    onStatusChanged: () -> Unit
) {
    // Usamos estados locales para que la UI se refresque al escribir
    var score1 by remember { mutableStateOf(combate.tocadosT1.toString()) }
    var score2 by remember { mutableStateOf(combate.tocadosT2.toString()) }
    var isTerminado by remember { mutableStateOf(combate.terminado) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isTerminado) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Tirador 1
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = combate.tirador1.apellido,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = if (isTerminado && combate.tocadosT1 > combate.tocadosT2) FontWeight.ExtraBold else FontWeight.Normal
                    )
                    Text(text = combate.tirador1.club, style = MaterialTheme.typography.bodySmall)
                }

                // Marcador Central
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    OutlinedTextField(
                        value = score1,
                        onValueChange = {
                            if (it.all { char -> char.isDigit() } && it.length <= 2) {
                                score1 = it
                                combate.tocadosT1 = it.toIntOrNull() ?: 0
                            }
                        },
                        modifier = Modifier.width(55.dp),
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )

                    Text(" - ", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 4.dp))

                    OutlinedTextField(
                        value = score2,
                        onValueChange = {
                            if (it.all { char -> char.isDigit() } && it.length <= 2) {
                                score2 = it
                                combate.tocadosT2 = it.toIntOrNull() ?: 0
                            }
                        },
                        modifier = Modifier.width(55.dp),
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }

                // Tirador 2
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text(
                        text = combate.tirador2.apellido,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.End,
                        fontWeight = if (isTerminado && combate.tocadosT2 > combate.tocadosT1) FontWeight.ExtraBold else FontWeight.Normal
                    )
                    Text(text = combate.tirador2.club, style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.End)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para finalizar el combate
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isTerminado,
                    onCheckedChange = {
                        isTerminado = it
                        combate.terminado = it
                        onStatusChanged()
                    }
                )
                Text(
                    text = if (isTerminado) "Combate Cerrado" else "Marcar como terminado",
                    style = MaterialTheme.typography.labelMedium
                )

                if (isTerminado) {
                    val ganador = if (combate.tocadosT1 > combate.tocadosT2) combate.tirador1.apellido else combate.tirador2.apellido
                    Text(
                        text = "🏆 Ganador: $ganador",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}