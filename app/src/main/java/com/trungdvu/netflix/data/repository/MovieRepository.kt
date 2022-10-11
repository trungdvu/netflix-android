package com.trungdvu.netflix.data.repository

import com.trungdvu.netflix.data.remote.MovieApiService
import com.trungdvu.netflix.model.HomeMovieListState
import com.trungdvu.netflix.model.MovieListResponse
import com.trungdvu.netflix.model.Result
import com.trungdvu.netflix.model.SimilarMovieListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieApiService: MovieApiService
) {
    suspend fun getTopRatedMovies(): Flow<HomeMovieListState<MovieListResponse>> = flow {
        val res = movieApiService.getTopRatedMovies(
            apiKey = "569137e6b761b50e2a92cd0a7afd29ea",
            page = 1,
            language = "en"
        )
        emit(HomeMovieListState.fromResult(Result.Success(res)))
    }.catch {
        emit(HomeMovieListState.fromResult(Result.Error("Something went wrong")))
    }.onStart {
        emit(HomeMovieListState(loading = true))
    }

    suspend fun getSimilarMovies(movieId: Long): Flow<HomeMovieListState<SimilarMovieListResponse>> =
        flow {
            val res = movieApiService.getSimilarMovies(
                apiKey = "569137e6b761b50e2a92cd0a7afd29ea",
                movieId = movieId,
            )
            emit(HomeMovieListState.fromResult(Result.Success(res)))
        }.catch {
            emit(HomeMovieListState.fromResult(Result.Error("Something went wrong")))
        }.onStart {
            emit(HomeMovieListState(loading = true))
        }
}