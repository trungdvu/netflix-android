package com.trungdvu.netflix.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trungdvu.netflix.data.repository.MovieRepository
import com.trungdvu.netflix.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PreviewMovieViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {
    var selectedMovie by mutableStateOf<Result<Movie?>>(Result.Success(null))

    val similarMovies =
        MutableStateFlow<MovieListState<SimilarMovieListResponse>>(MovieListState())

    fun setSelectedMovie(movie: Movie?) {
        selectedMovie = Result.Success(movie)
    }

    fun fetchSimilarMovies(movieId: Long) {
        viewModelScope.launch {
            repository.getSimilarMovies(movieId).collect { s ->
                similarMovies.value = s
            }
        }
    }
}