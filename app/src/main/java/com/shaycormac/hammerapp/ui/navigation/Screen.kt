package com.shaycormac.hammerapp.ui.navigation

import com.shaycormac.hammerapp.R

sealed class Screen(
    val route: String,
    val title: String,
    val icon: Int = 0,
) {
    object MenuScreen : Screen(
        route = "menu",
        title = "Меню",
        icon = R.drawable.menu
    )
    
    object ProfileScreen : Screen(
        route = "profile",
        title = "Профиль",
        icon = R.drawable.profile
    )

    object CartScreen : Screen(
        route = "cart",
        title = "Корзина",
        icon = R.drawable.cart
    )
}
