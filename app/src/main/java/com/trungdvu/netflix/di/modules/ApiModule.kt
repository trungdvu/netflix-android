package com.trungdvu.netflix.di.modules

import com.trungdvu.netflix.data.client.MovieApiClient
import com.trungdvu.netflix.data.remote.MovieApiService
import com.trungdvu.netflix.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    fun provideHttpClient(): OkHttpClient = MovieApiClient.createHttpClient()

    @Provides
    @Singleton
    fun provideMovieApiService(client: OkHttpClient): MovieApiService =
        MovieApiClient.createMoviesService(client)

    @Provides
    @Singleton
    fun provideMovieRepository(service: MovieApiService): MovieRepository =
        MovieRepository(service)
}