package com.trungdvu.netflix.ui.navigation

sealed class Screen(open val route: String = "") {
    object Home: Screen("Home")
    object Settings: Screen("settings")
    object MovieDetail: Screen("movie_detail")
}