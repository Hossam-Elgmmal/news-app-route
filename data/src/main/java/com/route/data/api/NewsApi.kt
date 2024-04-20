package com.route.data.api

import com.route.data.BuildConfig
import com.route.data.articles.ArticlesResponse
import com.route.data.sources.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = BuildConfig.API_KEY
interface NewsApi {

    @GET("top-headlines/sources")
    suspend fun getNewsSources(
        @Query("apiKey") api: String = API_KEY,
        @Query("category") category: String? = "",
    ): SourcesResponse

    @GET("everything")
    suspend fun getNewsBySource(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("pageSize") pageSize: Int = 10,
        @Query("sources") sourcesId: String,
    ): ArticlesResponse

    @GET("everything")
    suspend fun searchEverything(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("pageSize") pageSize: Int = 10,
        @Query("q") searchText: String,
    ): ArticlesResponse
}