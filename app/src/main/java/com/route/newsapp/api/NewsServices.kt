package com.route.newsapp.api

import com.route.newsapp.models.articles.ArticlesResponse
import com.route.newsapp.models.sources.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("top-headlines/sources")
    suspend fun getNewsSources(
        @Query("apiKey") api: String,
        @Query("category") category: String? = ""
    ): SourcesResponse

    @GET("everything")
    suspend fun getNewsBySource(
        @Query("apikey") api: String,
        @Query("sources") sourcesId: String
    ): ArticlesResponse

    @GET("everything")
    suspend fun searchEverything(
        @Query("apikey") apiKey: String,
        @Query("q") searchText: String
    ): ArticlesResponse
}