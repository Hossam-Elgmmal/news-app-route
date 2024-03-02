package com.route.newsapp.api

import com.route.newsapp.model.ArticlesResponse
import com.route.newsapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("top-headlines/sources")
    fun getNewsSources(

        @Query("apiKey")
        api: String,

        @Query("category")
        category: String? = ""

    ): Call<SourcesResponse>


    @GET("everything")
    fun getNewsBySource(

        @Query("apikey")
        api: String,

        @Query("sources")
        sourcesId: String

    ): Call<ArticlesResponse>
}