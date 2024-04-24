package com.route.domain.repository

import com.route.domain.models.ArticlesItemDto
import javax.inject.Inject

class ArticlesUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(sourcesId: String): List<ArticlesItemDto> {
        return repository.getArticles(sourcesId)
    }

    suspend fun getNextPage(sourcesId: String, page: Int): List<ArticlesItemDto> {
        return repository.getNextPage(sourcesId, page)
    }

    suspend fun search(searchText: String): List<ArticlesItemDto> {
        return repository.search(searchText)
    }

    suspend fun getArticleDetails(title: String): ArticlesItemDto {
        return repository.getArticleDetails(title)
    }
}