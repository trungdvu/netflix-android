package com.trungdvu.netflix.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trungdvu.netflix.ui.theme.NetflixTheme

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val movies by homeViewModel.movieListState.collectAsState()

    LazyColumn(
        modifier = Modifier.background(NetflixTheme.colors.appBackground)
    ) {
        when {
            movies.loading -> {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(top = 16.dp),
                            color = NetflixTheme.colors.brand
                        )
                    }
                }
            }

            movies.isSuccessful -> {
                items(movies.data!!.results) { movie ->
                    Text(
                        text = movie.originalTitle,
                        maxLines = 1,
                        color = NetflixTheme.colors.textPrimary
                    )
                }
            }
        }
    }
}