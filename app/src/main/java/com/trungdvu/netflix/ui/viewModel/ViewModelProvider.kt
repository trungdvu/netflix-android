package com.trungdvu.netflix.ui.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trungdvu.netflix.ui.screens.dashboard.home.view_model.HomeViewModel
import com.trungdvu.netflix.ui.screens.dashboard.home.view_model.NetflixOriginalViewModel
import com.trungdvu.netflix.ui.screens.dashboard.home.view_model.NowPlayingViewModel
import com.trungdvu.netflix.ui.screens.dashboard.home.view_model.PopularViewModel
import com.trungdvu.netflix.ui.screens.detail.viewModel.MovieVideosViewModel
import com.trungdvu.netflix.ui.screens.detail.viewModel.VideoViewModel

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

    val previewMovieViewModel: PreviewMovieViewModel
        @Composable
        get() = LocalPreviewMovieViewModel.current

    val videoViewModel: VideoViewModel
        @Composable
        get() = LocalVideoViewModel.current

    val movieVideosViewModel: MovieVideosViewModel
        @Composable
        get() = LocalMovieVideoByIdViewModel.current

    val appViewModel: AppViewModel
        @Composable
        get() = LocalAppViewModel.current
}

@Composable
fun ProvideMultiViewModel(content: @Composable () -> Unit) {
    val homeViewModel: HomeViewModel = viewModel()
    val nowPlayingViewModel: NowPlayingViewModel = viewModel()
    val popularViewModel: PopularViewModel = viewModel()
    val netflixOriginalViewModel: NetflixOriginalViewModel = viewModel()
    val previewMovieViewModel: PreviewMovieViewModel = viewModel()
    val videoViewModel: VideoViewModel = viewModel()
    val movieVideosViewModel: MovieVideosViewModel = viewModel()
    val appViewModel: AppViewModel = viewModel()

    CompositionLocalProvider(
        LocalAppViewModel provides appViewModel
    ) {
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
                            LocalPreviewMovieViewModel provides previewMovieViewModel
                        ) {
                            CompositionLocalProvider(
                                LocalVideoViewModel provides videoViewModel
                            ) {
                                CompositionLocalProvider(
                                    LocalMovieVideoByIdViewModel provides movieVideosViewModel,
                                ) {
                                    content()
                                }
                            }
                        }
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

private val LocalPreviewMovieViewModel = staticCompositionLocalOf<PreviewMovieViewModel> {
    error("No SelectedMovieViewModel provided")
}

private val LocalVideoViewModel = staticCompositionLocalOf<VideoViewModel> {
    error("No VideoViewModel provided")
}

private val LocalMovieVideoByIdViewModel = staticCompositionLocalOf<MovieVideosViewModel> {
    error("No MovieVideoByIdViewModel provided")
}

private val LocalAppViewModel = staticCompositionLocalOf<AppViewModel> {
    error("No AppViewModel provided")
}
