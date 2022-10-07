package com.trungdvu.netflix.data.repository

import android.util.Log
import com.trungdvu.netflix.data.remote.MovieApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieApiService: MovieApiService
) {
    suspend fun getTopRatedMovies() {
        val result = movieApiService.getTopRatedMovies(
            apiKey = "569137e6b761b50e2a92cd0a7afd29ea",
            page = 1,
            language = "en"
        )

        Log.d("result", result.toString())
    }
}