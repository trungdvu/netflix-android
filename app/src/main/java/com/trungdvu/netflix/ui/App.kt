package com.trungdvu.netflix.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trungdvu.netflix.ui.navigation.RootNavigation
import com.trungdvu.netflix.ui.screens.home.HomeViewModel

@Composable
fun App(splashScreenVisibleCondition: (SplashScreen.KeepOnScreenCondition) -> Unit) {
    val context = LocalContext.current
    var isOnline by remember {
        mutableStateOf(checkIfOnline(context))
    }

    if (isOnline) {
        val homeViewModel: HomeViewModel = viewModel()
        RootNavigation(homeViewModel)
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
