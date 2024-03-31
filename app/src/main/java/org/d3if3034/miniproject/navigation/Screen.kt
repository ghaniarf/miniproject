package org.d3if3034.miniproject.navigation

sealed class Screen (val route: String) {
    data object LandingPage: Screen("landingPage")
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
}