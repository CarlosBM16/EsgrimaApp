package com.example.esgrima.navigation

sealed class RootScreen {
    object Login : RootScreen()
    object Register : RootScreen()
    object Home : RootScreen()

}

sealed class MainDestination {
    object Home : MainDestination()
}