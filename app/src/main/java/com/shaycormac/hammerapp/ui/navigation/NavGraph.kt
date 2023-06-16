package com.shaycormac.hammerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shaycormac.hammerapp.ui.screen.CartScreen
import com.shaycormac.hammerapp.ui.screen.menu.MenuScreen
import com.shaycormac.hammerapp.ui.screen.ProfileScreen
import com.shaycormac.hammerapp.ui.screen.viewmodel.MenuViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    menuViewModel: MenuViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MenuScreen.route
    ) {
        composable(route = Screen.MenuScreen.route) {
            MenuScreen(menuViewModel)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen()
        }
        composable(route = Screen.CartScreen.route) {
            CartScreen()
        }
    }
}
