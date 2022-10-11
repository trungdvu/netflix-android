package com.trungdvu.netflix.ui.screens.dashboard.home.components

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
import com.trungdvu.netflix.ui.components.LargeMovieItem
import com.trungdvu.netflix.ui.theme.NetflixTheme

@Composable
fun LargeMovieSection(
    title: String,
    onMovieClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    movies: List<Movie>
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.25.sp
            ),
            color = NetflixTheme.colors.textPrimary,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        LargeMovieSectionCarousel(movies = movies, onMovieSelected = onMovieClick)
    }
}

@Composable
private fun LargeMovieSectionCarousel(
    movies: List<Movie>,
    onMovieSelected: (Long) -> Unit
) {
    LazyRow(modifier = Modifier.padding(start = 8.dp)) {
        items(movies) { movie ->
            LargeMovieItem(movie, onMovieSelected = onMovieSelected)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}