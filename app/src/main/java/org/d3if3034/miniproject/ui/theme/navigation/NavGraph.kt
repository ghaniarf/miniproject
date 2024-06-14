package org.d3if3034.miniproject.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3034.miniproject.ui.theme.screen.MainScreen
import org.d3if3034.miniproject.ui.theme.screen.AboutScreen
import org.d3if3034.miniproject.ui.theme.screen.LandingPage
import org.d3if3034.miniproject.ui.theme.screen.MainViewModel

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.LandingPage.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.LandingPage.route) {
            LandingPage(navController)
        }
    }
}