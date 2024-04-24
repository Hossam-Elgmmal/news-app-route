package com.route.domain.repository

import com.route.domain.models.ArticlesItemDto

interface ArticleRepository {
    suspend fun getArticles(sourcesId: String): List<ArticlesItemDto>
    suspend fun getNextPage(sourcesId: String, page: Int): List<ArticlesItemDto>
    suspend fun search(searchText: String): List<ArticlesItemDto>
    suspend fun getArticleDetails(title: String): ArticlesItemDto
}

interface OnlineArticles {
    suspend fun getArticles(sourcesId: String): List<ArticlesItemDto>
    suspend fun getNextPage(sourcesId: String, page: Int): List<ArticlesItemDto>
    suspend fun search(searchText: String): List<ArticlesItemDto>
}

interface OfflineArticles {
    suspend fun getArticles(sourcesId: String): List<ArticlesItemDto>
    suspend fun saveArticles(articlesList: List<ArticlesItemDto>)
    suspend fun getArticleDetails(title: String): ArticlesItemDto
}