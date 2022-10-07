package com.trungdvu.netflix.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun MovieDetailScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.background(Color.Red)
    ) {
        Text(text = "Home Screen")
        TextButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text(text = "Go to settings")
        }
    }
}