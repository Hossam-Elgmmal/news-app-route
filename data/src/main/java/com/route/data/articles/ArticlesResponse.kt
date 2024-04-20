package com.route.data.articles

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticleItem> = emptyList(),

    @field:SerializedName("status")
    val status: String? = null
)