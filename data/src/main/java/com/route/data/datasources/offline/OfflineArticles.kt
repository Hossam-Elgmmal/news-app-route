package com.route.data.datasources.offline

import com.route.data.articles.ArticleItem
import com.route.data.database.ArticlesDao
import com.route.domain.models.ArticlesItemDto
import com.route.domain.repository.OfflineArticles

class OfflineArticlesImpl(
    private val dao: ArticlesDao
) : OfflineArticles {
    override suspend fun getArticles(sourcesId: String): List<ArticlesItemDto> {
        return dao.getArticles(sourcesId).map {
            it.toDto()
        }.reversed()
    }

    override suspend fun saveArticles(articlesList: List<ArticlesItemDto>) {
        dao.saveArticles(articlesList.map {
            ArticleItem(
                it.title,
                it.sourcesId,
                it.publishedAt,
                it.author,
                it.urlToImage,
                it.description,
                it.url,
                it.content
            )
        })
    }

    override suspend fun getArticleDetails(title: String): ArticlesItemDto {
        return dao.getArticleDetails(title).toDto()
    }
}