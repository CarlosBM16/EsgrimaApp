package com.example.esgrima.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.esgrima.data.DataRepository
import com.example.esgrima.navigation.RootScreen.Competicion
import com.example.esgrima.ui.Home
import com.example.esgrima.ui.arbitros.CrearArbitros
import com.example.esgrima.ui.arbitros.ListaArbitros
import com.example.esgrima.ui.auth.Login
import com.example.esgrima.ui.competiciones.CrearCompeticiones
import com.example.esgrima.ui.competiciones.ListaCompeticiones
import com.example.esgrima.ui.tiradores.CrearTiradores
import com.example.esgrima.ui.tiradores.ListaTiradores

@Composable
fun RootNavigationGraph() {
    var currentScreen by remember { mutableStateOf<RootScreen>(RootScreen.Login) }

    var competicionSeleccionada by remember { mutableStateOf<com.example.esgrima.model.competicion.Competicion?>(null) }

    when(currentScreen) {
        RootScreen.Login -> Login(
            onLoginSuccess = { currentScreen = RootScreen.Home }
        )

        RootScreen.Home -> Home(
            onClickCrearTiradores = { currentScreen = RootScreen.CrearTiradores },
            onClickCrearArbitros = { currentScreen = RootScreen.CrearArbitros },
            onClickListaCompeticiones = { currentScreen = RootScreen.ListaCompeticiones },
            onClickLogOut = { currentScreen = RootScreen.Login },
            onClickListaTiradores = { currentScreen = RootScreen.ListaTiradores },
            onClickListaArbitros = { currentScreen = RootScreen.ListaArbitros },
            onClickCrearCompeticiones = { currentScreen = RootScreen.CrearCompeticiones }
        )

        RootScreen.CrearTiradores -> CrearTiradores(
            onTiradorGuardado = { nuevoTirador ->
                // Llamamos al repositorio
                DataRepository.guardadoTirador(nuevoTirador)
                // Navegamos hacia atrás o a la lista
                currentScreen = RootScreen.Home
            },
            onBack = { currentScreen = RootScreen.Home }
        )

        RootScreen.ListaTiradores -> ListaTiradores(
            onBack = { currentScreen = RootScreen.Home }
        )

        RootScreen.CrearArbitros -> CrearArbitros(
            onBack = { currentScreen = RootScreen.Home },
            onArbitroGuardado = { nuevoArbitro ->
                // Llamamos al repositorio
                DataRepository.guardadoArbitro(nuevoArbitro)
                // Navegamos hacia atrás o a la lista
                currentScreen = RootScreen.Home

            }
        )

        RootScreen.ListaArbitros -> ListaArbitros(
            onBack = { currentScreen = RootScreen.Home }
        )

        RootScreen.CrearCompeticiones -> CrearCompeticiones(
            onBack = { currentScreen = RootScreen.Home },
            onCompeticionGuardada = { nuevaCompeticion ->
                DataRepository.guardadoCompeticion(nuevaCompeticion)
                currentScreen = RootScreen.Home
            }
        )

        RootScreen.ListaCompeticiones -> ListaCompeticiones(
            onBack = { currentScreen = RootScreen.Home },
            onCompeticionClick = { competicion ->
                competicionSeleccionada = competicion
                currentScreen = RootScreen.Competicion
            }
        )

        RootScreen.Competicion -> {
            competicionSeleccionada?.let { comp ->
                com.example.esgrima.ui.competiciones.Competicion(
                    competicion = comp,
                    onBack = { currentScreen = RootScreen.ListaCompeticiones }
                )
            }
        }


        else -> {}
    }
}