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
import com.trungdvu.netflix.ui.components.onBottomSheetTapped
import com.trungdvu.netflix.ui.screens.dashboard.home.components.HighlightedMovie
import com.trungdvu.netflix.ui.screens.dashboard.home.components.LargeMovieSection
import com.trungdvu.netflix.ui.screens.dashboard.home.components.MovieSection
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
    val topRatedMovies by ViewModelProvider.homeViewModel.topRatedMovies.collectAsState()
    val nowPlayingMovies by ViewModelProvider.nowPlayingViewModel.nowPlayingMovies.collectAsState()
    val popularMovies by ViewModelProvider.popularViewModel.popularMovies.collectAsState()
    val netflixOriginalMovies by ViewModelProvider.netflixOriginalViewModel.netflixOriginalMovies.collectAsState()
    val previewMovieViewModel = ViewModelProvider.previewMovieViewModel

    NetflixSurface(
        color = NetflixTheme.colors.appBackground,
    ) {
        LazyColumn(
            state = scrollState,
        ) {
            item {
                when {
                    topRatedMovies.isSuccessful -> {
                        HighlightedMovie(
                            onClick = {
                                previewMovieViewModel.setSelectedMovie(topRatedMovies.data!!.results[1])

                                onBottomSheetTapped(
                                    coroutineScope = coroutineScope,
                                    bottomSheetScaffoldState = bottomSheetScaffoldState
                                )
                            },
                            modifier = modifier,
                            movie = topRatedMovies.data!!.results[1]
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        MovieSection(
                            title = "Trending Now",
                            onMovieClick = { movieId ->
                                val movie = topRatedMovies.data!!.results.find {
                                    it.id == movieId
                                }
                                previewMovieViewModel.setSelectedMovie(movie)

                                onBottomSheetTapped(
                                    coroutineScope = coroutineScope,
                                    bottomSheetScaffoldState = bottomSheetScaffoldState
                                )
                            },
                            modifier = Modifier,
                            movies = topRatedMovies.data!!.results
                        )
                    }

                }

                Spacer(modifier = Modifier.height(10.dp))
                when {
                    nowPlayingMovies.isSuccessful -> {
                        MovieSection(
                            title = "Trending Now",
                            onMovieClick = { movieId ->
                                val movie = nowPlayingMovies.data!!.results.find {
                                    it.id == movieId
                                }
                                previewMovieViewModel.setSelectedMovie(movie)

                                onBottomSheetTapped(
                                    coroutineScope = coroutineScope,
                                    bottomSheetScaffoldState = bottomSheetScaffoldState
                                )
                            },
                            modifier = Modifier,
                            movies = nowPlayingMovies.data!!.results
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                when {
                    netflixOriginalMovies.isSuccessful -> {
                        LargeMovieSection(
                            title = "Only on Netflix",
                            onMovieClick = { movieId ->
                                previewMovieViewModel.setSelectedMovie(netflixOriginalMovies.data!!.results.find {
                                    it.id == movieId
                                })
                                onBottomSheetTapped(
                                    coroutineScope = coroutineScope,
                                    bottomSheetScaffoldState = bottomSheetScaffoldState
                                )
                            },
                            modifier = Modifier,
                            movies = netflixOriginalMovies.data!!.results
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                when {
                    popularMovies.isSuccessful -> {
                        MovieSection(
                            title = "Popular on Netflix",
                            onMovieClick = { movieId ->
                                previewMovieViewModel.setSelectedMovie(popularMovies.data!!.results.find {
                                    it.id == movieId
                                })
                                onBottomSheetTapped(
                                    coroutineScope = coroutineScope,
                                    bottomSheetScaffoldState = bottomSheetScaffoldState
                                )
                            },
                            modifier = Modifier,
                            movies = popularMovies.data!!.results
                        )
                    }
                }


                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}