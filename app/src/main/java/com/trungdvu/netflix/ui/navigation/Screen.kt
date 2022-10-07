package com.trungdvu.netflix.ui.navigation

sealed class Screen(open val route: String = "") {
    object Home : Screen("home")
    object Settings : Screen("settings")
    object MovieDetail : Screen("movie_detail")
    object Splash : Screen("splash")
}