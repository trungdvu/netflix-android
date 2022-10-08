package com.trungdvu.netflix.ui.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trungdvu.netflix.model.Movie
import com.trungdvu.netflix.ui.components.SmallMovieItem
import com.trungdvu.netflix.ui.theme.NetflixTheme

@Composable
fun TrendingNowSection(
    onMovieClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    trendingNowMovies: List<Movie>
) {
    Column(modifier = modifier) {
        Text(
            text = "Trending Now",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.25.sp
            ),
            color = NetflixTheme.colors.textPrimary,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        TrendingNowMoviesCarousel(movies = trendingNowMovies, onMovieSelected = onMovieClick)
    }
}

@Composable
private fun TrendingNowMoviesCarousel(
    movies: List<Movie>,
    onMovieSelected: (Long) -> Unit
) {
    LazyRow(modifier = Modifier.padding(start = 8.dp)) {
        items(movies) { movie ->
            SmallMovieItem(movie, onMovieSelected = onMovieSelected)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}