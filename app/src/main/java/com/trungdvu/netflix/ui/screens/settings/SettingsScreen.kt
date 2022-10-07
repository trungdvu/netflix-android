package com.trungdvu.netflix.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun SettingsScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.background(Color.Black)
    ) {
        Text(text = "Settings Screen")
        TextButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text(text = "Back to home screen")
        }
    }
}
