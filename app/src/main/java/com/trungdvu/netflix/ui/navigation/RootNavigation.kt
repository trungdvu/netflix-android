package com.trungdvu.netflix.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trungdvu.netflix.ui.screens.detail.MovieDetailScreen
import com.trungdvu.netflix.ui.screens.home.HomeScreen
import com.trungdvu.netflix.ui.screens.settings.SettingsScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigation() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -300 },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -300 },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        }
    ) {
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Settings.route,
        ) {
            SettingsScreen(navController)
        }
        composable(
            route = Screen.MovieDetail.route,
        ) {
            MovieDetailScreen(navController)
        }
    }
}