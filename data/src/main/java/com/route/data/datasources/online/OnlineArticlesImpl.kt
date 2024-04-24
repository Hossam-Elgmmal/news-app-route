package com.route.data.datasources.online

import com.route.data.api.NewsApi
import com.route.domain.models.ArticlesItemDto
import com.route.domain.repository.OnlineArticles

class OnlineArticlesImpl(private val newsApi: NewsApi) : OnlineArticles {
    override suspend fun getArticles(sourcesId: String): List<ArticlesItemDto> {
        return newsApi.getNewsBySource(sourcesId = sourcesId).articles.map {
            it.sourcesId = sourcesId
            it.toDto()
        }
    }

    override suspend fun getNextPage(sourcesId: String, page: Int): List<ArticlesItemDto> {
        return newsApi.getNewsBySource(sourcesId = sourcesId, page = page).articles.map {
            it.sourcesId = sourcesId
            it.toDto()
        }
    }

    override suspend fun search(searchText: String): List<ArticlesItemDto> {
        return newsApi.searchEverything(searchText = searchText).articles.map {
            it.toDto()
        }
    }
}