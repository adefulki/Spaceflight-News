package io.github.adefulki.spaceflightnews.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavGraph(
    modifier: Modifier = Modifier,
    navigateToWelcome: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToDetail: () -> Unit,
    navigateUp: () -> Unit,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Welcome.route
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Welcome.route) {
        }
        composable(route = Screen.Home.route) {
        }
        composable(route = Screen.Detail.route) {
        }
    }
}