package org.d3if3034.miniproject.navigation

import org.d3if3034.miniproject.ui.screen.KEY_ID_PRAKTIKUM

sealed class Screen (val route: String) {
    data object LandingPage: Screen("landingPage")
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Hitung: Screen("hitungScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_PRAKTIKUM}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}