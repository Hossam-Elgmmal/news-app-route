package com.route.domain.models

data class ArticlesItemDto(

    val title: String = "",
    val sourcesId: String = "",
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val url: String? = null,
    val content: String? = null
)