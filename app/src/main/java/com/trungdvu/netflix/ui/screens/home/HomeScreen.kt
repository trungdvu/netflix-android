package com.trungdvu.netflix.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.trungdvu.netflix.ui.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    Column(
        modifier = Modifier.background(Color.Blue)
    ) {
        Text(text = "Home Screen")
        TextButton(
            onClick = {
                navController.navigate(Screen.Settings.route)
            }
        ) {
            Text(text = "Go to settings", color = Color.White)
        }
        TextButton(
            onClick = {
                navController.navigate(Screen.MovieDetail.route)
            },
        ) {
            Text(text = "Go to movie detail", color = Color.White)
        }
    }
}