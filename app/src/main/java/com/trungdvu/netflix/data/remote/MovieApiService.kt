package com.trungdvu.netflix.data.remote

import com.trungdvu.netflix.data.constant.ApiConstant
import com.trungdvu.netflix.model.Movie
import com.trungdvu.netflix.model.MovieListResponse
import com.trungdvu.netflix.model.MoviesVideoListResponse
import com.trungdvu.netflix.model.SimilarMovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET(ApiConstant.ENDPOINT_TOP_RATED)
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
    ): MovieListResponse

    @GET(ApiConstant.ENDPOINT_MOVIE)
    suspend fun getMovieById(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String
    ): Movie

    @GET(ApiConstant.ENDPOINT_NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
    ): MovieListResponse

    @GET(ApiConstant.ENDPOINT_POPULAR)
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
    ): MovieListResponse

    @GET(ApiConstant.ENDPOINT_MOVIE_VIDEO)
    suspend fun getMovieVideosById(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String
    ): MoviesVideoListResponse

    @GET(ApiConstant.ENDPOINT_SIMILAR_MOVIES)
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String
    ): SimilarMovieListResponse
}