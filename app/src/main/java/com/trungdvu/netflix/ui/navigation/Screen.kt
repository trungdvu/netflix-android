package com.trungdvu.netflix.ui.navigation

sealed class Screen(open val route: String = "") {
    object Splash : Screen("splash")
    object Dashboard : Screen("dashboard")
    object DashboardHome : Screen("dashboard/home")
    object DashboardPlaySomething : Screen("dashboard/play_something")
    object DashboardDownloads : Screen("dashboard/downloads")
    object DashboardNewAndHot : Screen("dashboard/new_and_hot")
}