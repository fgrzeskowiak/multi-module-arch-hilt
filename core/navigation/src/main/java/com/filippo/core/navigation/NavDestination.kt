package com.filippo.core.navigation

sealed class NavDestination {
    object HomeDestination : NavDestination()
    data class Login(val origin: NavDestination) : NavDestination()
    object Account : NavDestination()
    object Movies: NavDestination()
}
