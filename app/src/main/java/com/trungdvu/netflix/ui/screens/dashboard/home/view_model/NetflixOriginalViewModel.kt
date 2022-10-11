package com.trungdvu.netflix.ui.screens.dashboard.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trungdvu.netflix.data.repository.MovieRepository
import com.trungdvu.netflix.model.MovieListState
import com.trungdvu.netflix.model.MovieListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetflixOriginalViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    val netflixOriginalMovies =
        MutableStateFlow<MovieListState<MovieListResponse>>(MovieListState())

    init {
        getOriginalMovies()
    }

    private fun getOriginalMovies() {
        viewModelScope.launch {
            movieRepository.getNetflixOriginalMovies().collect { s ->
                netflixOriginalMovies.value = s
            }
        }
    }
}
