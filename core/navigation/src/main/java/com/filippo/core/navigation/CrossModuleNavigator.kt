package com.filippo.core.navigation

import androidx.navigation.NavController

object CrossModuleNavigator {
    fun navigateTo(destination: NavDestination, navController: NavController) {
        when (destination) {
            NavDestination.Account -> navController.navigate(MainNavGraphDirections.openAccount())
            is NavDestination.Login -> navController.navigate(MainNavGraphDirections.openLogin(0))
            else -> {
            }
        }
    }
}
