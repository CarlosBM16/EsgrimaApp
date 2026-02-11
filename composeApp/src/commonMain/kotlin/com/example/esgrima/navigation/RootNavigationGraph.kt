package com.example.esgrima.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.esgrima.ui.Home
import com.example.esgrima.ui.auth.Login

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
            onClickListaCompeticiones = { currentScreen = RootScreen.ListaCompeticiones }
        )

        else -> {}
    }
}