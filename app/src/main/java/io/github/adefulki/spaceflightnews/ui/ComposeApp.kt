package io.github.adefulki.spaceflightnews.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import io.github.adefulki.spaceflightnews.ui.theme.AppTheme

@Composable
fun ComposeApp(defaultRoute: String) {
    AppTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            MyActions(navController)
        }

        MyNavGraph(
            navController = navController,
            navigateToWelcome = navigationActions.navigateToWelcome,
            navigateToHome = navigationActions.navigateToHome,
            navigateToDetail = navigationActions.navigateToDetail,
            navigateUp = navigationActions.navigateUp,
            startDestination = defaultRoute
        )
    }
}