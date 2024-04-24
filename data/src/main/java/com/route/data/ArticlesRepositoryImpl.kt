package com.route.data

import com.route.domain.models.ArticlesItemDto
import com.route.domain.repository.ArticleRepository
import com.route.domain.repository.NetworkHandler
import com.route.domain.repository.OfflineArticles
import com.route.domain.repository.OnlineArticles
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val onlineArticles: OnlineArticles,
    private val offlineArticles: OfflineArticles,
    private val networkHandler: NetworkHandler,
) : ArticleRepository {
    override suspend fun getArticles(sourcesId: String): List<ArticlesItemDto> {
        if (networkHandler.isOnline()) {
            val articles = onlineArticles.getArticles(sourcesId)
            offlineArticles.saveArticles(articles)
            return articles
        }
        return offlineArticles.getArticles(sourcesId)
    }

    override suspend fun getNextPage(sourcesId: String, page: Int): List<ArticlesItemDto> {
        return onlineArticles.getNextPage(sourcesId, page)
    }

    override suspend fun search(searchText: String): List<ArticlesItemDto> {
        if (networkHandler.isOnline()) {
            val articles = onlineArticles.search(searchText)
            offlineArticles.saveArticles(articles)
            return articles
        }
        return emptyList()
    }

    override suspend fun getArticleDetails(title: String): ArticlesItemDto {
        return offlineArticles.getArticleDetails(title)
    }
}