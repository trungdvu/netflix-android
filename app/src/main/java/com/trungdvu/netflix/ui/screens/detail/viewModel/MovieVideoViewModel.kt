package com.trungdvu.netflix.ui.screens.detail.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trungdvu.netflix.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieVideosViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movieVideo = MutableLiveData("")
    val movieVideo: LiveData<String> = _movieVideo

    fun fetchMovieVideoById(movieId: Long) {
        viewModelScope.launch {
            repository.getMovieVideosById(movieId = movieId)
                .subscribe(
                    {},
                    { data ->
                        _movieVideo.value = data.url
                    }
                )
        }
    }
}