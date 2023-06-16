package com.shaycormac.hammerapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shaycormac.hammerapp.ui.navigation.NavGraph
import com.shaycormac.hammerapp.ui.navigation.Screen
import com.shaycormac.hammerapp.ui.screen.viewmodel.MenuViewModel
import com.shaycormac.hammerapp.ui.theme.GrayB1
import com.shaycormac.hammerapp.ui.theme.GrayB2
import com.shaycormac.hammerapp.ui.theme.RedB1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen() {

    val navController = rememberNavController()
    val menuViewModel: MenuViewModel = hiltViewModel()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        NavGraph(
            navController = navController,
            menuViewModel = menuViewModel
        )
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        Screen.MenuScreen,
        Screen.ProfileScreen,
        Screen.CartScreen,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        modifier = Modifier
            .background(GrayB2),
        label = {
            Text(
                text = screen.title,
                style = MaterialTheme.typography.headlineSmall,
                color = if (
                    currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true
                ) RedB1 else GrayB1,
            )
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Ícone De Navegação",
                modifier = Modifier.size(24.dp),
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = GrayB1,
        selectedContentColor = RedB1,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

