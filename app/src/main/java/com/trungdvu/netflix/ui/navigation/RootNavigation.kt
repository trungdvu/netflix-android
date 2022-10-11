package com.trungdvu.netflix.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.trungdvu.netflix.ui.screens.dashboard.downloads.DownloadsScreen
import com.trungdvu.netflix.ui.screens.dashboard.home.HomeScreen
import com.trungdvu.netflix.ui.screens.dashboard.new_and_hot.NewAndHotScreen
import com.trungdvu.netflix.ui.screens.dashboard.play_something.PlaySomethingScreen
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
//    mainNavActions: MainActions
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
    }
}

class MainActions(
    navController: NavHostController,
    updateAppBarVisibility: (Boolean) -> Unit
) {
    val openMovieDetails = { movieId: Long ->
        updateAppBarVisibility(false)
        navController.navigate("${"movieId"}/$movieId") {
            // Pop up to the start destination of the graph to avoid building up a large
            // stack of destinations on the back stack as users select items
            popUpTo(navController.graph.startDestinationId)
            // Avoid multiple copies of the same destination when re-selecting the same item
            launchSingleTop = true
        }
    }
    val upPress: (rom: NavBackStackEntry) -> Unit = { from: NavBackStackEntry ->
        // In order to discard duplicated navigation events, we check the Lifecycle
//        if (from.lifecycleIsResumed()) {
//            updateAppBarVisibility(true)
//            navController.navigateUp()
//        }
    }
}

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
