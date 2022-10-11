package com.trungdvu.netflix.ui.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trungdvu.netflix.ui.screens.dashboard.home.view_model.HomeViewModel
import com.trungdvu.netflix.ui.screens.dashboard.home.view_model.NetflixOriginalViewModel
import com.trungdvu.netflix.ui.screens.dashboard.home.view_model.NowPlayingViewModel
import com.trungdvu.netflix.ui.screens.dashboard.home.view_model.PopularViewModel

object ViewModelProvider {
    val homeViewModel: HomeViewModel
        @Composable
        get() = LocalTopRatedMoviesViewModel.current

    val nowPlayingViewModel: NowPlayingViewModel
        @Composable
        get() = LocalNowPlayingViewModel.current

    val popularViewModel: PopularViewModel
        @Composable
        get() = LocalPopularViewModel.current

    val netflixOriginalViewModel: NetflixOriginalViewModel
        @Composable
        get() = LocalNetflixOriginalViewModel.current

    val selectedMovieViewModel: PreviewMovieViewModel
        @Composable
        get() = LocalSelectedMovieViewModel.current

}

@Composable
fun ProvideMultiViewModel(content: @Composable () -> Unit) {
    val homeViewModel: HomeViewModel = viewModel()
    val nowPlayingViewModel: NowPlayingViewModel = viewModel()
    val popularViewModel: PopularViewModel = viewModel()
    val netflixOriginalViewModel: NetflixOriginalViewModel = viewModel()
    val previewMovieViewModel: PreviewMovieViewModel = viewModel()

    CompositionLocalProvider(
        LocalTopRatedMoviesViewModel provides homeViewModel
    ) {
        CompositionLocalProvider(
            LocalNowPlayingViewModel provides nowPlayingViewModel
        ) {
            CompositionLocalProvider(
                LocalPopularViewModel provides popularViewModel
            ) {
                CompositionLocalProvider(
                    LocalNetflixOriginalViewModel provides netflixOriginalViewModel
                ) {
                    CompositionLocalProvider(
                        LocalSelectedMovieViewModel provides previewMovieViewModel
                    ) {
                        content()
                    }
                }
            }
        }
    }
}

private val LocalTopRatedMoviesViewModel = staticCompositionLocalOf<HomeViewModel> {
    error("No HomeViewModel provided")
}

private val LocalNowPlayingViewModel = staticCompositionLocalOf<NowPlayingViewModel> {
    error("No NowPlayingViewModel provided")
}

private val LocalNetflixOriginalViewModel = staticCompositionLocalOf<NetflixOriginalViewModel> {
    error("No NetflixOriginalViewModel provided")
}

private val LocalPopularViewModel = staticCompositionLocalOf<PopularViewModel> {
    error("No PopularViewModel provided")
}

private val LocalSelectedMovieViewModel = staticCompositionLocalOf<PreviewMovieViewModel> {
    error("No SelectedMovieViewModel provided")
}