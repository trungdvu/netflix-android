package com.trungdvu.netflix.data.client

import com.trungdvu.netflix.data.constant.ApiConstant
import com.trungdvu.netflix.data.remote.MovieApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiClient {
    fun createHttpClient(): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder().build()
            return@Interceptor chain.proceed(request)
        }
        val httpClient = OkHttpClient.Builder().addInterceptor(requestInterceptor)
        return httpClient.build()
    }

    fun createMoviesService(
        client: OkHttpClient
    ): MovieApiService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(ApiConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }
}