package com.trungdvu.netflix.ui.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trungdvu.netflix.ui.screens.dashboard.home.HomeViewModel

object ViewModelProvider {
    val homeViewModel: HomeViewModel
        @Composable
        get() = LocalTopRatedMoviesViewModel.current

    val selectedMovieViewModel: PreviewMovieViewModel
        @Composable
        get() = LocalSelectedMovieViewModel.current

}

@Composable
fun ProvideMultiViewModel(content: @Composable () -> Unit) {
    val homeViewModel: HomeViewModel = viewModel()
    val previewMovieViewModel: PreviewMovieViewModel = viewModel()

    CompositionLocalProvider(
        LocalTopRatedMoviesViewModel provides homeViewModel
    ) {
        CompositionLocalProvider(
            LocalSelectedMovieViewModel provides previewMovieViewModel
        ) {
            content()
        }
    }
}

private val LocalTopRatedMoviesViewModel = staticCompositionLocalOf<HomeViewModel> {
    error("No HomeViewModel provided")
}

private val LocalSelectedMovieViewModel = staticCompositionLocalOf<PreviewMovieViewModel> {
    error("No SelectedMovieViewModel provided")
}