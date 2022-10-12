package com.trungdvu.netflix.data.repository

import com.trungdvu.netflix.data.remote.MovieApiService
import com.trungdvu.netflix.model.*
import com.trungdvu.netflix.util.Single
import com.trungdvu.netflix.util.success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieApiService: MovieApiService
) {
    suspend fun getTopRatedMovies(): Flow<MovieListState<MovieListResponse>> = flow {
        val res = movieApiService.getTopRatedMovies(
            apiKey = "569137e6b761b50e2a92cd0a7afd29ea",
            page = 1,
            language = "en"
        )
        emit(MovieListState.fromResult(Result.Success(res)))
    }.catch {
        emit(MovieListState.fromResult(Result.Error("Something went wrong")))
    }.onStart {
        emit(MovieListState(loading = true))
    }

    suspend fun getTopTrendingMovies(): Flow<MovieListState<MovieListResponse>> = flow {
        val res = movieApiService.getNowPlayingMovies(
            apiKey = "569137e6b761b50e2a92cd0a7afd29ea",
            page = 1,
            language = "en"
        )
        emit(MovieListState.fromResult(Result.Success(res)))
    }.catch {
        emit(MovieListState.fromResult(Result.Error("Something went wrong")))
    }.onStart {
        emit(MovieListState(loading = true))
    }

    suspend fun getNetflixOriginalMovies(): Flow<MovieListState<MovieListResponse>> = flow {
        val res = movieApiService.getNetflixOriginalMovies(
            apiKey = "569137e6b761b50e2a92cd0a7afd29ea",
            page = 1,
            language = "en"
        )
        emit(MovieListState.fromResult(Result.Success(res)))
    }.catch {
        emit(MovieListState.fromResult(Result.Error("Something went wrong")))
    }.onStart {
        emit(MovieListState(loading = true))
    }


    suspend fun getPopularMovies(): Flow<MovieListState<MovieListResponse>> = flow {
        val res = movieApiService.getPopularMovies(
            apiKey = "569137e6b761b50e2a92cd0a7afd29ea",
            page = 1,
            language = "en"
        )
        emit(MovieListState.fromResult(Result.Success(res)))
    }.catch {
        emit(MovieListState.fromResult(Result.Error("Something went wrong")))
    }.onStart {
        emit(MovieListState(loading = true))
    }


    suspend fun getSimilarMovies(movieId: Long): Flow<MovieListState<SimilarMovieListResponse>> =
        flow {
            val res = movieApiService.getSimilarMovies(
                apiKey = "569137e6b761b50e2a92cd0a7afd29ea",
                movieId = movieId,
            )
            emit(MovieListState.fromResult(Result.Success(res)))
        }.catch {
            emit(MovieListState.fromResult(Result.Error("Something went wrong")))
        }.onStart {
            emit(MovieListState(loading = true))
        }


    suspend fun getMovieVideosById(
        movieId: Long
    ): Single<Error, MovieVideo> {
        return try {
            val result = movieApiService.getMovieVideosById(
                movieId = movieId,
                apiKey = "569137e6b761b50e2a92cd0a7afd29ea",
            ).results.first().asDomainModel()
            success(result)
        } catch (e: Exception) {
            Timber.tag("getMovieVideosById").e("Exception: ${e.message}")
            error(com.trungdvu.netflix.util.Error.UnexpectedError)
        }
    }
}