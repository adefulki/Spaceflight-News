package io.github.adefulki.spaceflightnews.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val path: String? = null,
    vararg val arguments: NamedNavArgument
) {
    data object Welcome : Screen("welcome")
    data object Home : Screen("home")
    data object Detail : Screen("detail")
}

class MyActions(navController: NavController) {
    val navigateToWelcome: () -> Unit = {
        navController.navigate(Screen.Welcome.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    }
    val navigateToHome: () -> Unit = {
        navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    }
    val navigateToDetail: () -> Unit = {
        navController.navigate(Screen.Detail.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}