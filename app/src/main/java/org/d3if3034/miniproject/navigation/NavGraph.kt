package org.d3if3034.miniproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3034.miniproject.ui.screen.AboutScreen
import org.d3if3034.miniproject.ui.screen.DetailScreen
import org.d3if3034.miniproject.ui.screen.HitungScreen
import org.d3if3034.miniproject.ui.screen.KEY_ID_PRAKTIKUM
import org.d3if3034.miniproject.ui.screen.LandingPage
import org.d3if3034.miniproject.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.LandingPage.route
    ) {
        composable(route = Screen.LandingPage.route) {
            LandingPage(navController)
        }
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.Hitung.route) {
            HitungScreen(navController)
        }
        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_PRAKTIKUM) { type = NavType.LongType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_PRAKTIKUM)
            DetailScreen(navController, id)
        }
    }
}