package com.trungdvu.netflix.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.trungdvu.netflix.ui.screens.dashboard.downloads.DownloadsScreen
import com.trungdvu.netflix.ui.screens.dashboard.home.HomeScreen
import com.trungdvu.netflix.ui.screens.dashboard.new_and_hot.NewAndHotScreen
import com.trungdvu.netflix.ui.screens.dashboard.play_something.PlaySomethingScreen
import com.trungdvu.netflix.ui.screens.detail.MovieDetailsScreen
import com.trungdvu.netflix.ui.screens.splash.AnimatedSplashScreen
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun RootNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Splash.route,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    bottomSheetCoroutineScope: CoroutineScope,
    homeScreenScrollState: LazyListState,
    mainNavActions: MainActions
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(
            route = Screen.Splash.route,
        ) {
            AnimatedSplashScreen(navController)
        }

        navigation(
            route = Screen.Dashboard.route,
            startDestination = Screen.DashboardHome.route
        ) {
            composable(Screen.DashboardHome.route) {
                HomeScreen(
                    bottomSheetScaffoldState = bottomSheetScaffoldState,
                    modifier = modifier,
                    coroutineScope = bottomSheetCoroutineScope,
                    scrollState = homeScreenScrollState
                )
            }
            composable(Screen.DashboardPlaySomething.route) {
                PlaySomethingScreen()
            }
            composable(Screen.DashboardNewAndHot.route) {
                NewAndHotScreen()
            }
            composable(Screen.DashboardDownloads.route) {
                DownloadsScreen()
            }
        }

        composable(
            route = "${Screen.MovieDetails.route}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.LongType }),
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
        ) { from: NavBackStackEntry ->

            BackHandler {
                mainNavActions.upPress(from)
            }

            val arguments = requireNotNull(from.arguments)
            val movieId = arguments.getLong("movieId")

            MovieDetailsScreen(
                movieId = movieId,
                upPress = {
                    mainNavActions.upPress(from)
                }
            )
        }

    }
}

class MainActions(
    navController: NavHostController,
    updateAppBarVisibility: (Boolean) -> Unit
) {
    val openMovieDetails = { movieId: Long ->
        updateAppBarVisibility(false)
        navController.navigate("${Screen.MovieDetails.route}/$movieId") {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }
    val upPress: (from: NavBackStackEntry) -> Unit = { from: NavBackStackEntry ->
        if (from.lifecycleIsResumed()) {
            updateAppBarVisibility(true)
            navController.navigateUp()
        }
    }
}

fun NavBackStackEntry.lifecycleIsResumed() = this.lifecycle.currentState == Lifecycle.State.RESUMED

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
