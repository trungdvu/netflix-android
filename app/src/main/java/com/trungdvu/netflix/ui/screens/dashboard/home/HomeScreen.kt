package com.trungdvu.netflix.ui.screens.dashboard.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trungdvu.netflix.ui.components.NetflixSurface
import com.trungdvu.netflix.ui.screens.dashboard.home.components.HighlightedMovie
import com.trungdvu.netflix.ui.screens.dashboard.home.components.TrendingNowSection
import com.trungdvu.netflix.ui.theme.NetflixTheme
import com.trungdvu.netflix.ui.viewModel.ViewModelProvider
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
    scrollState: LazyListState
) {
    val movies by ViewModelProvider.homeViewModel.movieListState.collectAsState()

    NetflixSurface(
        color = NetflixTheme.colors.appBackground
    ) {
        LazyColumn(
            state = scrollState,
        ) {
            item {
                HighlightedMovie(
                    onClick = {},
                    modifier = Modifier,
                    movie = movies.data!!.results[1]
                )
                Spacer(modifier = Modifier.height(10.dp))
                TrendingNowSection(
                    onMovieClick = { movieId ->
                    },
                    modifier = Modifier,
                    trendingNowMovies = movies.data!!.results
                )
                Spacer(modifier = Modifier.height(10.dp))
                TrendingNowSection(
                    onMovieClick = { movieId ->
                    },
                    modifier = Modifier,
                    trendingNowMovies = movies.data!!.results
                )
            }
        }
    }
}