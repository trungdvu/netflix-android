package com.trungdvu.netflix.ui.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trungdvu.netflix.ui.navigation.Screen
import com.trungdvu.netflix.ui.theme.NetflixTheme
import kotlinx.coroutines.delay
import com.trungdvu.netflix.R
import com.trungdvu.netflix.ui.screens.home.HomeViewModel

@Composable
fun AnimatedSplashScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    var startAnimation by remember { mutableStateOf(false) }
    val movieListState by homeViewModel.movieListState.collectAsState()
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1250)
        when {
            movieListState.isSuccessful -> {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
        }
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(NetflixTheme.colors.appBackground)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(id = R.drawable.netflix_logo),
            contentDescription = "Netflix Logo",
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
        )
    }
}
