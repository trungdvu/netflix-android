package com.trungdvu.netflix.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trungdvu.netflix.data.repository.MovieRepository
import com.trungdvu.netflix.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.trungdvu.netflix.model.Result
import kotlinx.coroutines.launch

@HiltViewModel
class PreviewMovieViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {

    var selectedMovie by mutableStateOf<Result<Movie?>>(Result.Success(null))
        private set

    var similarMovies by mutableStateOf<Result<List<Movie?>>>(Result.Success(listOf()))
        private set

    fun setSelectedMovie(movie: Movie?) {
        selectedMovie = Result.Success(movie)
    }

    fun fetchSimilarMovies(movieId: Long) {
        viewModelScope.launch {
        }
    }
}