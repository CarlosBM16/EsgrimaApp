package com.example.esgrima.navigation

sealed class RootScreen {
    object Login : RootScreen()
    object Home : RootScreen()
    object CrearTiradores : RootScreen()
    object CrearArbitros : RootScreen()
    object ListaCompeticiones : RootScreen()
    object ListaTiradores : RootScreen()
    object ListaArbitros : RootScreen()
    object CrearCompeticiones : RootScreen()

    object Competicion : RootScreen()
}

sealed class MainDestination {
    object Home : MainDestination()
}