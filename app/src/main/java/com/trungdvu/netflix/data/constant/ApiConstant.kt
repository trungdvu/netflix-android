package com.trungdvu.netflix.data.constant

object ApiConstant {
    const val BASE_URL = "http://api.themoviedb.org/3/"

    const val ENDPOINT_POPULAR = "movie/popular"
    const val ENDPOINT_TOP_RATED = "movie/top_rated"
    const val ENDPOINT_NOW_PLAYING = "movie/now_playing"
    const val ENDPOINT_MOVIE = "movie/{movie_id}"
    const val ENDPOINT_MOVIE_VIDEO = "movie/{movie_id}/videos"
    const val ENDPOINT_SIMILAR_MOVIES = "movie/{movie_id}/similar"
    const val NETFLIX_ORIGINAL = "/discover/tv?with_networks=213"

    const val LANG_ENG = "en-US"
    const val IMAGE_BASE_URL_W500 = "https://image.tmdb.org/t/p/w500"

    const val SAMPLE_VIDEO_URL =
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
}