package com.trungdvu.netflix.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trungdvu.netflix.data.repository.MovieRepository
import com.trungdvu.netflix.model.HomeMovieListState
import com.trungdvu.netflix.model.MovieListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    val movieListState =
        MutableStateFlow<HomeMovieListState<MovieListResponse>>(HomeMovieListState())

    init {
        fetchTopRatedMovies()
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            movieRepository.getTopRatedMovies().collect { s ->
                movieListState.value = s
            }
        }
    }
}