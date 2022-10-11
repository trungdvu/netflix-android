package com.trungdvu.netflix.model

data class MovieListState<T>(
    val loading: Boolean = false,
    val data: T? = null,
    val error: String? = null
) {
    val isSuccessful = error == null && !loading

    companion object {
        fun <T : Any> fromResult(result: Result<T>): MovieListState<T> = when (result) {
            is Result.Success -> MovieListState(data = result.data)
            is Result.Error -> MovieListState(error = result.error)
        }
    }
}
