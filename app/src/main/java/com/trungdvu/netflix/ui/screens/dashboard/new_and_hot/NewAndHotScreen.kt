package com.trungdvu.netflix.ui.screens.dashboard.new_and_hot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.trungdvu.netflix.ui.components.NetflixSurface

@Composable
fun NewAndHotScreen() {
    NetflixSurface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "New and hot screen")
        }
    }
}