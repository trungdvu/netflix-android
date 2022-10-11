package com.trungdvu.netflix.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.trungdvu.netflix.R
import com.trungdvu.netflix.ui.theme.NetflixTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var splashScreen: SplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_Netflix)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppContent {
                splashScreen.setKeepOnScreenCondition(it)
            }
        }
    }

    @Composable
    fun AppContent(splashScreenVisibleCondition: (SplashScreen.KeepOnScreenCondition) -> Unit) {
        NetflixTheme {
            Surface(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .fillMaxSize(),
            ) {
                App()
            }
        }
    }
}