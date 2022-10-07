package com.trungdvu.netflix.model

data class HomeMovieListState<T>(
    val loading: Boolean = false,
    val data: T? = null,
    val error: String? = null
) {
    val isSuccessful = error == null && !loading

    companion object {
        fun <T : Any> fromResult(result: Result<T>): HomeMovieListState<T> = when (result) {
            is Result.Success -> HomeMovieListState(data = result.data)
            is Result.Error -> HomeMovieListState(error = result.error)
        }
    }
}
