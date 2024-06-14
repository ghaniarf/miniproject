package org.d3if3034.miniproject.ui.theme.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object LandingPage: Screen("landingPage")
}