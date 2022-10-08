package com.trungdvu.netflix.ui

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trungdvu.netflix.ui.components.DashboardSections
import com.trungdvu.netflix.ui.components.NetflixBottomBar
import com.trungdvu.netflix.ui.components.NetflixScaffold
import com.trungdvu.netflix.ui.components.TopBar
import com.trungdvu.netflix.ui.navigation.RootNavigation
import com.trungdvu.netflix.ui.screens.home.HomeViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App(splashScreenVisibleCondition: (SplashScreen.KeepOnScreenCondition) -> Unit) {
    val context = LocalContext.current
    var isOnline by remember {
        mutableStateOf(checkIfOnline(context))

    }
    val tabs = remember { DashboardSections.values() }
    val (shouldShowAppBar, updateAppBarVisibility) = remember { mutableStateOf(true) }
    val homeScreenScrollState = rememberLazyListState()
    val isScrolledDown = remember {
        derivedStateOf {
            homeScreenScrollState.firstVisibleItemScrollOffset > 0
        }
    }
    val homeViewModel: HomeViewModel = viewModel()
    val navController = rememberAnimatedNavController()

    if (isOnline) {
        NetflixScaffold(

            bottomBar = { NetflixBottomBar(navController = navController, tabs = tabs) },
            fab = {
                if (shouldShowAppBar) {
//                    PlaySomethingFAB(isScrolledUp = isScrolledDown.value.not())
                }
            }
        ) { innerPaddingModifier ->
            if (shouldShowAppBar) {
                RootNavigation(navController, homeViewModel)
                TopBar(isScrolledDown = isScrolledDown.value)
            }
        }
    } else {
        OfflineDialog {
            isOnline = checkIfOnline(context)
        }
    }
}

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = "No Internet") },
        text = { Text(text = "No internet connection. Turn on Wifi or mobile data.") },
        confirmButton = {
            TextButton(onClick = onRetry) {
                Text("Retry")
            }
        }
    )
}

@Suppress("DEPRECATION")
private fun checkIfOnline(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}
