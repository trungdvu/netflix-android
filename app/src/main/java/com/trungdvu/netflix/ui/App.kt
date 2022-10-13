package com.trungdvu.netflix.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trungdvu.netflix.ui.components.*
import com.trungdvu.netflix.ui.navigation.MainActions
import com.trungdvu.netflix.ui.navigation.RootNavigation
import com.trungdvu.netflix.ui.viewModel.ProvideMultiViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.trungdvu.netflix.ui.screens.dashboard.DashboardSections
import com.trungdvu.netflix.ui.screens.dashboard.NetflixBottomBar
import com.trungdvu.netflix.ui.theme.NetflixTheme
import com.trungdvu.netflix.ui.viewModel.ViewModelProvider

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun App() {
    ProvideMultiViewModel {
        val context = LocalContext.current
        val appViewModel = ViewModelProvider.appViewModel
        var isOnline by remember {
            mutableStateOf(checkIfOnline(context))
        }
        val navController = rememberAnimatedNavController()
        val tabs = remember { DashboardSections.values() }
        val bottomSheetCoroutineScope = rememberCoroutineScope()
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        )
        val homeScreenScrollState = rememberLazyListState()
        val isScrolledDown = remember {
            derivedStateOf {
                homeScreenScrollState.firstVisibleItemScrollOffset > 0
            }
        }
        val mainNavActions = remember(navController) {
            MainActions(navController, appViewModel.updateAppBarVisibility)
        }

        if (isOnline) {
            BottomSheetScaffold(
                scaffoldState = bottomSheetScaffoldState,
                sheetContent = {
                    BottomSheetContent(
                        onMovieClick = { movieId: Long ->
                            closeBottomSheet(bottomSheetCoroutineScope, bottomSheetScaffoldState)
                            mainNavActions.openMovieDetails(movieId)
                        },
                        onBottomSheetClosePressed = {
                            closeBottomSheet(bottomSheetCoroutineScope, bottomSheetScaffoldState)
                        }
                    )
                },
                sheetPeekHeight = 0.dp
            ) {
                NetflixScaffold(
                    bottomBar = {
                        NetflixBottomBar(
                            navController = navController,
                            tabs = tabs
                        )
                    },
                    fab = {
                        if (appViewModel.shouldShowAppBar.value) {
                            PlaySomethingFAB(isScrolledUp = isScrolledDown.value.not())
                        }
                    }
                ) { innerPaddingModifier ->
                    RootNavigation(
                        navController = navController,
                        modifier = Modifier.padding(innerPaddingModifier),
                        bottomSheetScaffoldState = bottomSheetScaffoldState,
                        bottomSheetCoroutineScope = bottomSheetCoroutineScope,
                        homeScreenScrollState = homeScreenScrollState,
                        mainNavActions = mainNavActions
                    )
                    if (appViewModel.shouldShowAppBar.value) {
                        TopBar(isScrolledDown = isScrolledDown.value)
                    }
                }
            }
        } else {
            OfflineDialog {
                isOnline = checkIfOnline(context)
            }
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

@ExperimentalMaterialApi
private fun closeBottomSheet(
    bottomSheetCoroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    bottomSheetCoroutineScope.launch {
        bottomSheetScaffoldState.bottomSheetState.collapse()
    }
}

private val ExtendedFabTextPadding = 20.dp

@Composable
fun getFabTextTextPaddingState(isScrolledDown: Boolean): State<Dp> {
    return animateDpAsState(
        targetValue = if (isScrolledDown) 0.dp else ExtendedFabTextPadding,
    )
}

@ExperimentalAnimationApi
@Composable
fun PlaySomethingFAB(
    isScrolledUp: Boolean
) {
    FloatingActionButton(
        onClick = {},
        backgroundColor = NetflixTheme.colors.progressIndicatorBg,
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    ) {
        val fabTextPadding = getFabTextTextPaddingState(isScrolledDown = isScrolledUp.not()).value
        Box(
            modifier = Modifier.padding(
                start = fabTextPadding,
                end = fabTextPadding,
            ),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Shuffle",
                    tint = NetflixTheme.colors.iconTint
                )
                Spacer(Modifier.width(fabTextPadding))
                AnimatedVisibility(visible = isScrolledUp) {
                    Text(
                        text = "Play Something",
                        color = NetflixTheme.colors.uiLightBackground,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}