package com.example.esgrima.ui.competiciones

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.esgrima.data.DataRepository
import com.example.esgrima.model.competicion.Competicion

@Composable
fun SeccionClasificacion(competicion: Competicion) {
    val clasificacion = DataRepository.calcularClasificacion(competicion)

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text("#", modifier = Modifier.weight(0.5f), fontWeight = FontWeight.Bold)
                Text("Tirador", modifier = Modifier.weight(2f), fontWeight = FontWeight.Bold)
                Text("V/M", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Text("Ind.", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            }
        }
        itemsIndexed(clasificacion) { index, registro ->
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text("${index + 1}", modifier = Modifier.weight(0.5f))
                Text("${registro.tirador.nombre} ${registro.tirador.apellido}", modifier = Modifier.weight(2f))
                val vmRedondeado = (registro.v_m * 100).toInt() / 100.0
                Text(
                    text = vmRedondeado.toString(),
                    modifier = Modifier.weight(1f)
                )
                Text("${registro.indice}", modifier = Modifier.weight(1f))
            }
            HorizontalDivider()
        }
    }
}