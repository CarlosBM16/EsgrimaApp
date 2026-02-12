package com.example.esgrima.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.esgrima.data.DataRepository
import com.example.esgrima.ui.Home
import com.example.esgrima.ui.auth.Login
import com.example.esgrima.ui.tiradores.CrearTiradores
import com.example.esgrima.ui.tiradores.ListaTiradores

@Composable
fun RootNavigationGraph() {
    var currentScreen by remember { mutableStateOf<RootScreen>(RootScreen.Login) }

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

        RootScreen.ListaTiradores -> ListaTiradores()



        else -> {}
    }
}