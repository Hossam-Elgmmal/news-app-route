package com.route.newsapp.contarcts

import com.route.newsapp.models.sources.SourcesItem

interface SourcesRepository {
    suspend fun getSources(categoryId: String): List<SourcesItem>
}

interface SourcesOnlineDataSource {
    suspend fun fetchSources(categoryId: String): List<SourcesItem>
}

interface SourcesOfflineDataSource {
    suspend fun saveSources(sourcesList: List<SourcesItem>)

    suspend fun getSources(categoryId: String): List<SourcesItem>
}