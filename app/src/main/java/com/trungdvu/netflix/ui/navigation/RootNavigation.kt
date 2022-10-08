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
import com.trungdvu.netflix.ui.screens.home.HomeViewModel
import com.trungdvu.netflix.ui.screens.settings.SettingsScreen
import com.trungdvu.netflix.ui.screens.splash.AnimatedSplashScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigation(
    homeViewModel: HomeViewModel,
) {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(
            route = Screen.Splash.route,
        ) {
            AnimatedSplashScreen(navController, homeViewModel)
        }
        composable(
            route = Screen.Home.route,
//            enterTransition = {
//                slideInHorizontally(
//                    initialOffsetX = { 300 },
//                    animationSpec = tween(250)
//                ) + fadeIn(animationSpec = tween(250))
//            },
//            exitTransition = {
//                slideOutHorizontally(
//                    targetOffsetX = { -300 },
//                    animationSpec = tween(250)
//                ) + fadeOut(animationSpec = tween(250))
//            },
//            popEnterTransition = {
//                slideInHorizontally(
//                    initialOffsetX = { -300 },
//                    animationSpec = tween(250)
//                ) + fadeIn(animationSpec = tween(250))
//            }
        ) {
            HomeScreen(navController, homeViewModel)
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