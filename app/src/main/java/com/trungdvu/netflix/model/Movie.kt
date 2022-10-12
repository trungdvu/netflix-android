package com.trungdvu.netflix.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Long,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

data class MovieListResponse(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class SimilarMovie(
    val id: Long,
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backDropPath: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val title: String,
    val overview: String,
    val video: Boolean,
)

data class SimilarMovieListResponse(
    val results: List<Movie>
)

data class MovieVideo(
    val key: String,
    val url: String,
    val name: String,
    val size: Int,
    val type: String
)

data class MoviesVideoListResponse(
    val results: List<MovieVideoItemDto>
)

data class MovieVideoItemDto(
    val key: String,
    val name: String,
    val size: Int,
    val type: String
) {
    fun asDomainModel() = MovieVideo(
        key = key,
        url = "https://www.youtube.com/watch?v=$key",
        name = name,
        size = size,
        type = type
    )
}
